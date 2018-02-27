package com.lomen.mybatisdemo.plugin.pagehelper;


import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis---通用分页拦截器
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Intercepts(
        @Signature(
                type= Executor.class,
                method = "query",
                args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class}

        )
)
public class PageInterceptor implements Interceptor{

    private static final List<ResultMapping> EMPTY_RESULTMAPPING=new ArrayList<ResultMapping>();
    private Dialect dialect;
    private Field additionalParametersField;

    /**
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args=invocation.getArgs(); //获取拦截方法的参数
        MappedStatement ms =(MappedStatement)args[0];
        Object parameterObject=args[1];
        RowBounds rowBounds=(RowBounds) args[2];
        //调用方法判断是否需要进行分页，如果不需要，直接返回结果
        if(!dialect.skip(ms.getId(),parameterObject,rowBounds)){
            ResultHandler resultHandler=(ResultHandler)args[3];
            //当前的目标对象
            Executor executor=(Executor) invocation.getTarget();
            BoundSql boundSql=ms.getBoundSql(parameterObject);

            //反射获取动态参数
            Map<String,Object> addintionalParameters=(Map<String,Object>)additionalParametersField.get(boundSql);
            //判断是否需要进行count查询
            if(dialect.beforeCount(ms.getId(),parameterObject,rowBounds)){
                //根据当前的ms创建一个返回值为Long类型的ms
                MappedStatement countMs=newMappedStatement(ms,Long.class);
                //创建count查询的缓存key
                CacheKey countKey=executor.createCacheKey(countMs,
                        parameterObject,
                        RowBounds.DEFAULT,
                        boundSql);
                //调用方言获取count sql
                String countSql=dialect.getCountSql(boundSql,
                        parameterObject,
                        rowBounds,
                        countKey);
                BoundSql countBoundSql=new BoundSql(
                        ms.getConfiguration(),
                        countSql,
                        boundSql.getParameterMappings(),
                        parameterObject
                );
                //当使用动态sql时，可能会产生临时的参数
                //这些参数需要手动设置到新的BoundSQL中
                for(String key:addintionalParameters.keySet()){
                    countBoundSql.setAdditionalParameter(key,addintionalParameters.get(key));
                }
                //执行count查询
                Object countResultList=executor.query(
                        countMs,
                        parameterObject,
                        RowBounds.DEFAULT,
                        resultHandler,
                        countKey,
                        countBoundSql
                );
                Long count=(Long)((List)countResultList).get(0);
                //处理查询总数
                dialect.afterCount(count,parameterObject,rowBounds);
                if(count==0){
                    //当查询总数为0时，直接返回空的结果
                    return dialect.afterPage(new ArrayList(),
                            parameterObject,
                            rowBounds);
                }

                //判断是否需要进行分页查询
                if(dialect.beforePage(ms.getId(),parameterObject,rowBounds)){
                    //生成分页的缓存key
                    CacheKey pageKey=executor.createCacheKey(
                            ms,
                            parameterObject,
                            rowBounds,
                            boundSql
                    );
                    //调用方言获取分页sql
                    String pageSql=dialect.getPageSql(
                            boundSql,
                            parameterObject,
                            rowBounds,
                            pageKey
                    );
                    BoundSql pageBoundSql=new BoundSql(
                          ms.getConfiguration(),
                          pageSql,
                          boundSql.getParameterMappings(),
                          parameterObject
                    );
                    //设置动态参数
                    for(String key:addintionalParameters.keySet()){
                        pageBoundSql.setAdditionalParameter(
                                key,
                                addintionalParameters.get(key)
                        );
                    }
                    //执行分页查询
                    List resultList=executor.query(
                            ms,
                            parameterObject,
                            RowBounds.DEFAULT,
                            resultHandler,
                            pageKey,
                            pageBoundSql
                    );
                    return dialect.afterPage(resultList,parameterObject,rowBounds);
                }
            }
        }
        //返回默认查询
        return invocation.proceed();
    }

    /**
     * 根据现有的ms创建一个新的返回值类型，使用新的返回值类型
     * @param ms
     * @param resultType
     * @return
     */
    public MappedStatement newMappedStatement(MappedStatement ms,Class<?> resultType){
        MappedStatement.Builder builder=new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId()+"_Count",
                ms.getSqlSource(),
                ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if(ms.getKeyGenerator()!=null&&ms.getKeyProperties().length!=0){
            StringBuilder keyProperties=new StringBuilder();
            for(String keyProperty: ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1,keyProperties.length());
            builder.keyProperty(keyProperties.toString());

        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps=new ArrayList<ResultMap>();
        ResultMap resultMap=new ResultMap.Builder(ms.getConfiguration(),
                ms.getId()
                ,resultType,
                EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    public Object plugin(Object o) {

        return Plugin.wrap(o,this);
    }

    public void setProperties(Properties properties) {
        String dialectClass=properties.getProperty("dialect");
            try {
                dialect=(Dialect) Class.forName(dialectClass).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("使用PageInterceptor分页插件时，必须设置dialect属性");
            }
            dialect.setProperties(properties);
            try{
                //反射获取BoundSql中的additionalParameters属性

                additionalParametersField= BoundSql.class.getDeclaredField("additionalParameters");
                additionalParametersField.setAccessible(true);
            }
            catch (NoSuchFieldException e){
                throw new RuntimeException(e);
            }


    }
}
