package com.tiddar.miniframework.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类注解，用于标记实体并且若需要手动更改表名可传入注解value()实现手动调整实体对应的表的表名
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface Entity {
    String value() default  "";
}
