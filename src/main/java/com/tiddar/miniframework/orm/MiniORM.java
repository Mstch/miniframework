package com.tiddar.miniframework.orm;

import java.util.List;


/**
 * 牛逼的orm工具
 * <b><br>
 * 注意事项：&nbsp;&nbsp;<STRIKE>目前不带建表功能(以后没准会加上）</STRIKE> &nbsp;&nbsp;现在已经有了，若实例化时不传入表名，表名与类名相同<br>
 * 所有实体必须包含id createDate updateDate三个字段<br>
 * Param类见：
 * </b>
 *
 * @param <T>
 * @author tiddar
 * @see Param
 */
public interface MiniORM<T> {

    int insert(T[] insertEntities);

    int insert(T insertEntity);

    List<T> query(Param[] params);

    List<T> query(Param[] params, String orderBy, String orderMethod);

    Page<T> query(Param[] params, int num, int size, String orderBy, String orderMethod);

    T queryOne(Param[] params);

    int delete(Param[] params);

    int update(Param[] params, T newEntity);

    int update(Long id, T newEntity);


}
