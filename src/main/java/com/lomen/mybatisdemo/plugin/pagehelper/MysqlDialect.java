package com.lomen.mybatisdemo.plugin.pagehelper;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

public class MysqlDialect implements Dialect {
    public boolean skip(String msId, Object parameterObject, RowBounds rowBounds) {
        //这里使用RowBounds分页
        //没有RowBounds参数时，使用RowBounds.DEFAULT默认分页
        if (rowBounds != RowBounds.DEFAULT)
            return false;
        else
            return true;
    }

    public boolean beforeCount(String msId, Object parameterObject, RowBounds rowBounds) {
        //只有使用了PageRowBounds才记录总数，否则查询了总数也没用
        if(rowBounds instanceof PageRowBounds)
            return true;
        else
            return false;
    }

    public String getCountSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
        //简单嵌套实现Mysql count查询
        return "select count(1) from ("+boundSql.getSql()+") temp";
    }

    public void afterCount(long count, Object parameterObject, RowBounds rowBounds) {
        //记录总数，按照beforeCount逻辑，只有PageRowBounds时才会查询count
        //所以这里直接强制转换
        ((PageRowBounds)rowBounds).setTotal(count);
    }

    public boolean beforePage(String msId, Object parameterObject, RowBounds rowBounds) {
        if(rowBounds!=RowBounds.DEFAULT)
            return true;
        else
            return false;
    }

    public String getPageSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
        //pageKey会影响缓存，通过固定的RowBounds 可以保证二级缓存有效
        pageKey.update("RowBounds");
        return boundSql.getSql()+" limit "+rowBounds.getOffset()+","+rowBounds.getLimit();

    }

    public Object afterPage(List pageList, Object parameterObject, RowBounds rowBounds) {
        //直接返回查询结果
        return pageList;
    }

    public void setProperties(Properties properties) {

    }
}
