package com.tiddar.miniframework.entity;

import com.tiddar.miniframework.orm.annotation.Entity;

import java.util.Date;

/**
 * 测试用的实体类，里面包含id,createDate,updateDate,test四个字段
 */
@Entity
public class Test {
    Long id;
    Date createDate;
    Date updateDate;
    String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"test\":\"")
                .append(test).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
