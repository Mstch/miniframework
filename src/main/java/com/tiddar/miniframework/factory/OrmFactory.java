package com.tiddar.miniframework.factory;

import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.orm.DBConnection;
import com.tiddar.miniframework.orm.MiniORM;
import com.tiddar.miniframework.orm.MiniORMImpl;
import com.tiddar.miniframework.orm.annotation.Entity;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * orm工具管理类，
 */
public class OrmFactory {
    private static Map<String, MiniORM> ormHashMap = new HashMap<String, MiniORM> ();
    static  boolean hasInit = false;
    public static void init() {
        if(hasInit) return;
        DBConnection.init();
        String basePackage = Utility.getProperties("miniframework.entity.package");
        Set<Class<?>> classes = Utility.getClasses(basePackage);
        System.out.println("扫描到:"+classes.size()+"个实体类");
        classes.forEach(aClass -> {
            Annotation annotation = aClass.getAnnotation(Entity.class);
            if (annotation != null) {
                if (((Entity) annotation).value().length() == 0)
                    ormHashMap.put(aClass.getSimpleName(), new MiniORMImpl(aClass));
                else
                    ormHashMap.put(aClass.getSimpleName(), new MiniORMImpl(aClass,((Entity) annotation).value()));
                System.out.println(aClass.getSimpleName() + "的ORM工具实例已经装载");
            }
        });
        hasInit = true;
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
