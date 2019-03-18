package com.tiddar.miniframework.example;

import com.tiddar.miniframework.BootStrap;
import com.tiddar.miniframework.web.annotation.Api;
import com.tiddar.miniframework.web.annotation.Mapping;
import com.tiddar.miniframework.web.enums.MapperType;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Bootstrap;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("/test")
public class Test {
    @Mapping("/ss")
    public String ss(Integer a, HttpServletRequest req, HttpSession session) {
        return req.getAttribute("fuck").toString();
    }

    @Mapping(value = "/sss", type = MapperType.FORWARD)
    public String sss(Integer a, HttpServletRequest request, HttpSession session) {
        request.setAttribute("fuck",123);
        return "/test/ss";
    }

    public static void main(String[] args) throws Exception {
        BootStrap.enableOrm();
        BootStrap.enableWeb();
    }
}
