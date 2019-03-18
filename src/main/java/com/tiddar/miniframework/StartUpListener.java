package com.tiddar.miniframework;

import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.factory.OrmFactory;
import com.tiddar.miniframework.web.Dispatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebListener
public class StartUpListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public StartUpListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
//        System.out.println("mvc路由注册开始");
//        Dispatcher.webInit();
//        System.out.println("mvc路由注册结束");
//        System.out.println("动态添加servlet");
//        ServletContext sc = sce.getServletContext();
//        ServletRegistration servletRegistration = sc.addServlet("dispatcher", Dispatcher.class);
//        List<String> urlPatterns = new ArrayList<String>();
//        Arrays.asList((PropertiesUtil.getProperties(miniframework.api.allowsuffix").split(","))).forEach(prop->{
//            urlPatterns.add("*"+prop);
//        });
//        urlPatterns.add("/");
//        servletRegistration.addMapping(urlPatterns.toArray(new String[urlPatterns.size()]));
//        ((ServletRegistration.Dynamic) servletRegistration).setLoadOnStartup(0);
//        System.out.println("orm工具加载开始");
//        OrmFactory.init();
//        System.out.println("orm工具加载结束");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
