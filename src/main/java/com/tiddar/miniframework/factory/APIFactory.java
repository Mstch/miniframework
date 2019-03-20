package com.tiddar.miniframework.factory;

import com.tiddar.miniframework.BootStrap;
import com.tiddar.miniframework.common.PropertiesUtil;
import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.web.Dispatcher;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIFactory {
    static Logger logger = LogManager.getLogger(APIFactory.class);

    private static volatile Boolean webInit = false;

    public static void init(int port, String contextPath) throws ServletException, LifecycleException {
        if (!webInit) {
            logger.debug("内嵌tomcat启动开始");
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(port);
            tomcat.getHost().setAppBase(".");
            logger.debug("路由注册开始");
            Dispatcher.webInit();
            logger.debug("路由注册结束");
            Context context = tomcat.addWebapp(contextPath, Utility.getAbsolutePath(APIFactory.class) + "web");
            Wrapper wrapper = context.createWrapper();
            wrapper.setName("dispatcher");
            wrapper.setServlet(new Dispatcher());
            wrapper.setLoadOnStartup(0);
            context.addChild(wrapper);
            Arrays.asList((PropertiesUtil.getProperties("miniframework.api.allowsuffix").split(","))).forEach(prop -> {
                wrapper.addMapping("*" + prop);
            });
            wrapper.addMapping("/");
            logger.debug("dispatcher servlet的URL匹配模式注册的模式为{}",wrapper.findMappings());
            tomcat.start();
            logger.debug("内嵌tomcat启动完成");
            tomcat.getServer().await();

        }
    }
}
