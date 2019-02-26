package com.tiddar.miniframework.orm;

/**
 * SQL语句参数构造类，比如想查询name是qwe,password是asd的User表的一条记录<br>
 * 则对应的两个Param为<br>
 * 1:   logic: AND (默认为AND，为了与 WHERE 拼SQL语句第一个逻辑也是AND（实际上拼出来的WHERE子句为 WHERE 1=1 AND name = "qwe"))<br>
 *      relation： =<br>
 *      field: name<br>
 *      value:qwe<br>
 * 2: logic: AND <br>
 *      relation： =<br>
 *      field: name<br>
 *      value:asd<br>
 */
public class Param {
    public String logic;//逻辑 AND或者OR
    public String relation; //关系=或者like
    public String field;//参数的字段名
    public Object value;//参数值
    public Param() {
        this.logic = "and";
        this.relation = "=";
    }
    public Param and(){
        this.logic = "and";
        return this;
    }
    public Param or(){
        this.logic = "or";
        return this;
    }
    public Param equal(){
        this.relation = "=";
        return this;
    }
    public Param like(){
        this.relation = "like";
        return this;
    }
    public Param field(String field){
        this.field = " `"+field+"` ";
        return this;
    }
    public Param value(Object value){
        this.value = value;
        return this;
    }
}
