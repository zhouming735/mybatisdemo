package com.lomen.mybatisdemo.ultis;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceKey {
  String dataSource() default "defaultDataSource";
}
