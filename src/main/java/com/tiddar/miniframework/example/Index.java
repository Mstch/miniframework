package com.tiddar.miniframework.example;

import com.tiddar.miniframework.web.annotation.Api;
import com.tiddar.miniframework.web.annotation.Mapping;
import com.tiddar.miniframework.web.enums.MapperType;

import javax.servlet.http.HttpServletRequest;

@Api("/index")
public class Index {
    @Mapping(value = "",type = MapperType.FORWARD)
    public String index(HttpServletRequest request){
        request.setAttribute("test",2222);
        return "/index.jsp";
    }
}
