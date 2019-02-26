package com.tiddar.miniframework.orm;

import java.util.List;


/**
 * 牛逼的orm工具的核心接口
 * <b><br>
 * 注意事项：&nbsp;&nbsp;<STRIKE>目前不带建表功能(以后没准会加上）</STRIKE> &nbsp;&nbsp;现在已经有了，若实例化时不传入表名，表名与类名相同<br>
 * 所有实体必须包含id createDate updateDate三个字段<br>
 * Param类见：{@link com.tiddar.miniframework.orm.Param SQLWhere子句的参数类型--Param}
 * Page 类见：{@link com.tiddar.miniframework.orm.Page 分页的工具类，将分页查询得到的结果抽象}
 * </b>
 * 下面是一些基础的crud方法
 * @param <T> 对应实体类类型参数
 * @author tiddar
 *
 */
public interface MiniORM<T> {
    int insert(T[] insertEntities);

    int insert(T insertEntity);


    List<T> queryList(Param[] params);

    List<T> queryList(Param[] params, String orderBy, String orderMethod);
    Page<T> queryPage(Param[] params, int num, int size);
    Page<T> queryPage(Param[] params, int num, int size, String orderBy, String orderMethod);

    T queryOne(Param[] params);

    int delete(Param[] params);

    int update(Param[] params, T newEntity);

    int update(Long id, T newEntity);


}
