package com.tiddar.miniframework.orm;

import java.util.List;

/**
 * number       页码<b>从1开始</b>
 * size         每页大小
 * currentObjs  当前页数据
 * total       查询到的总数据
 * @param <T>
 */
public class Page<T> {
    /**
     * 页码<b>从1开始</b>
     */
    public int number;
    public int size;//每页大小
    public List<T> currentObjs;//当前页数据
    public int total;//查询到的总数据

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getCurrentObjs() {
        return currentObjs;
    }

    public void setCurrentObjs(List<T> currentObjs) {
        this.currentObjs = currentObjs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}