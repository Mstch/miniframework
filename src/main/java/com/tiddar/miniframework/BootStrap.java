package com.tiddar.miniframework;

import com.tiddar.miniframework.common.PropertiesUtil;
import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.factory.APIFactory;
import com.tiddar.miniframework.factory.OrmFactory;
import com.tiddar.miniframework.orm.DBConnection;
import com.tiddar.miniframework.orm.MiniORM;
import com.tiddar.miniframework.orm.MiniORMImpl;
import com.tiddar.miniframework.orm.annotation.Entity;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 启动类
 *
 * @author tiddar
 */
public class BootStrap {

    /**
     * 启动web支持
     *
     * @param port
     * @param contextPath
     * @throws Exception
     */
    public static void enableWeb(int port, String contextPath) throws Exception {
        APIFactory.init(port,contextPath);
    }


    public static void enableWeb() throws Exception {
        enableWeb(8080, "");
    }


    /**
     * 开启orm支持
     */
    public static void enableOrm() {

    }


}