package com.tiddar.miniframework.factory;

import com.tiddar.miniframework.common.PropertiesUtil;
import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.orm.DBConnection;
import com.tiddar.miniframework.orm.MiniORM;
import com.tiddar.miniframework.orm.MiniORMImpl;
import com.tiddar.miniframework.orm.annotation.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * orm工具管理类，
 * @author tiddar
 */
public class OrmFactory {
    static Logger logger = LogManager.getLogger(OrmFactory.class);
    public static Map<String, MiniORM> ormHashMap = new HashMap<String, MiniORM> ();
    private static volatile Boolean ormInit = false;

    /**
     * 初始化orm工厂，根据扫描到的实体类实例化对应的orm工具类对象
     */
    public static void init(){
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

    /**
     * 获取传入实体类类型，获取到orm接口的map中对应的orm接口
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> MiniORM<T> getOrm(Class<T> clazz) {
        return ormHashMap.get(clazz.getSimpleName());
    }
}
