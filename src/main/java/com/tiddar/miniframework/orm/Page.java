package com.tiddar.miniframework.orm;

import java.util.List;

public class Page<T> {
    public int number;//页码
    public int size;//每页大小
    public List<T> currentObjs;//当前页数据
    public int total;//查询到的总数据
}