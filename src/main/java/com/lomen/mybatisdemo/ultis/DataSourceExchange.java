package com.lomen.mybatisdemo.ultis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DataSourceExchange {

    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     */
    @Before("@annotation(com.lomen.mybatisdemo.ultis.DataSourceKey)")
    public void beforeDaoMethod(JoinPoint point) throws Exception {
        // 获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DataSourceKey dataSourceAnnotation = className.getAnnotation(DataSourceKey.class);
        if (dataSourceAnnotation != null) {
            // 获得访问的方法名
            String methodName = point.getSignature().getName();
            // 得到方法的参数的类型
            @SuppressWarnings("rawtypes")
            Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
            String dataSource = "defaultDataSource";
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DataSourceKey.class)) {
                    DataSourceKey annotation = method.getAnnotation(DataSourceKey.class);
                    dataSource = annotation.dataSource();
                    System.out.println("DataSource Aop ====> " + dataSource);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            DataSourceContextHolder.setDataSourceType(dataSource);
        }
    }

    /**
     * 提取目标对象方法注解和类注解中的数据源标识
     */
    @After("@annotation(com.lomen.mybatisdemo.ultis.DataSourceKey)")
    private void resetDataSource(JoinPoint point) {
        // 获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DataSourceKey dataSourceAnnotation = className.getAnnotation(DataSourceKey.class);
        if (dataSourceAnnotation != null) {
            // 获得访问的方法名
            String methodName = point.getSignature().getName();
            // 得到方法的参数的类型
            @SuppressWarnings("rawtypes")
            Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
            String dataSource = "defaultDataSource";
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DataSourceKey.class)) {
                    DataSourceKey annotation = method.getAnnotation(DataSourceKey.class);
                    dataSource = annotation.dataSource();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (dataSource != null && !"defaultDataSource".equals(dataSource))
                DataSourceContextHolder.clearDataSourceType();
        }
    }
}
