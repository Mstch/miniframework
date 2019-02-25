package com.tiddar.miniframework.test;

import com.tiddar.miniframework.common.Utility;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public String test(Integer i, Double d, String s) {
        System.out.println(i + "" + d + s);
        return i + "" + d + s;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Test().proxy(new Test(), Test.class.getDeclaredMethod("test", Integer.class, Double.class, String.class)));
    }

    private Object proxy(Object api, Method mapping) throws Exception {
        Class clazz = api.getClass();
        //得到该方法参数信息数组
        Parameter[] parameters = mapping.getParameters();
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("i", new String[]{"1"});
        parameterMap.put("d", new String[]{"1.2"});
        parameterMap.put("s", new String[]{"sssss"});
        List<Object> mappingParameters = new ArrayList<Object>(parameters.length);
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
            String[] values = parameterMap.get(parameter.getName());
            String mappingParamterType = parameter.getType().getName(); //field为反射出来的字段类型
            String mappingParamterTypeSimple = parameter.getType().getSimpleName();
            String value = values[0];
            if (parameter.getType() == String.class)
                mappingParameters.add(value);
            else if (mappingParamterType.indexOf("java.lang.") == 0) {
                Object param = Utility.convertStringToOtherType(parameter.getType(), value);
                mappingParameters.add(param);
            }
        }
        Object[] parameterArray = mappingParameters.toArray();
        return mapping.invoke(api, parameterArray);
    }
}
