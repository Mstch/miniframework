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
    public static Map<String, MiniORM> ormHashMap = new HashMap<String, MiniORM> ();
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
