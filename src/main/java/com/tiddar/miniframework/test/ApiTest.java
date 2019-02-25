package com.tiddar.miniframework.test;

import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.web.annotation.Api;
import com.tiddar.miniframework.web.annotation.Mapping;
import com.tiddar.miniframework.web.enums.MapperType;

import java.lang.reflect.InvocationTargetException;

@Api("/test")
public class ApiTest {

    @Mapping(value = "/dsb", type = MapperType.FORWARD)
    public String test(String a) {
        System.out.println("dsb");
        System.out.println(a);
        return "/test/sb";
    }

    @Mapping(value = "/sb", type = MapperType.JSON)
    public void test() {
        System.out.println("sb");
    }

}
