package com.tiddar.miniframework.web.annotation;

import com.tiddar.miniframework.web.enums.MapperType;
import com.tiddar.miniframework.web.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Mapping {
    public String value();
    public RequestMethod method() default  RequestMethod.GET;
    public MapperType type() default  MapperType.JSON;

}
