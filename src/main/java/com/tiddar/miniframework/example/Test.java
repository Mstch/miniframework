package com.tiddar.miniframework.example;

import com.tiddar.miniframework.web.annotation.Api;
import com.tiddar.miniframework.web.annotation.Mapping;

@Api("/test")
public class Test {


    @Mapping("/ss")
    public String sss(Integer a){
        System.out.println(a);
        return "1221";
    }
}
