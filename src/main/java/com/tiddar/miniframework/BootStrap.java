package com.tiddar.miniframework;

import com.tiddar.miniframework.common.Utility;
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
    static Logger logger = LogManager.getLogger(BootStrap.class);
    private static volatile Boolean webInit = false;
    private static volatile Boolean ormInit = false;

    /**
     * 启动web支持
     *
     * @param port
     * @param contextPath
     * @throws Exception
     */
    public static void enableWeb(int port, String contextPath) throws Exception {
        if (!webInit) {
            synchronized (webInit) {
                if (!webInit) {
                    logger.debug("开始启动内嵌tomcat");
                    Tomcat tomcat = new Tomcat();
                    tomcat.setPort(port);
                    tomcat.getHost().setAppBase(".");
                    tomcat.addWebapp(contextPath, getAbsolutePath() + "web");
                    tomcat.start();
                    logger.debug("启动内嵌tomcat完成");
                    tomcat.getServer().await();
                }
            }
        }
    }


    public static void enableWeb() throws Exception {
        enableWeb(8080, "");
    }

    /**
     * 获取class文件路径
     *
     * @return
     */
    private static String getAbsolutePath() {
        String path = null;
        String folderPath = BootStrap.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                .substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }

    /**
     * 开启orm支持
     */
    public static void enableOrm() {
        if (!ormInit) {
            synchronized (ormInit) {
                if (!ormInit) {
                    DBConnection.init();
                    String basePackage = PropertiesUtil.getProperties("miniframework.entity.package");
                    Set<Class<?>> classes = Utility.getClasses(basePackage);
                    logger.debug("开始初始化ORM模块");
                    classes.forEach(aClass -> {
                        Annotation annotation = aClass.getAnnotation(Entity.class);
                        if (annotation != null) {
                            if (((Entity) annotation).value().length() == 0) {
                                OrmFactory.ormHashMap.put(aClass.getSimpleName(), new MiniORMImpl(aClass));
                            } else {
                                OrmFactory.ormHashMap.put(aClass.getSimpleName(), new MiniORMImpl(aClass, ((Entity) annotation).value()));
                            }
                            logger.debug("{}的ORM工具实例已经装载", aClass.getSimpleName());
                        }
                    });
                    logger.debug("初始化ORM模块完成");
                    ormInit = true;
                }
            }
        }
    }


}