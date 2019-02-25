package com.tiddar.miniframework.web;


import com.tiddar.miniframework.web.enums.MapperType;
import com.tiddar.miniframework.web.enums.RequestMethod;

import java.lang.reflect.Method;

public class Mapper {
    public String url;
    public Object apiInstance;
    public Method method;
    public RequestMethod requestMethod;
    public MapperType mapperType;

    public Mapper(String url, Object apiInstance, Method method, RequestMethod requestMethod, MapperType mapperType) {
        this.url = url;
        this.apiInstance = apiInstance;
        this.method = method;
        this.requestMethod = requestMethod;
        this.mapperType = mapperType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"apiInstance\":")
                .append(apiInstance);
        sb.append(",\"method\":")
                .append(method);
        sb.append(",\"requestMethod\":")
                .append(requestMethod);
        sb.append(",\"mapperType\":")
                .append(mapperType);
        sb.append('}');
        return sb.toString();
    }
}
