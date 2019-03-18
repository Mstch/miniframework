package com.tiddar.miniframework.web;


import com.tiddar.miniframework.common.PropertiesUtil;
import com.tiddar.miniframework.common.Utility;
import com.tiddar.miniframework.web.annotation.Api;
import com.tiddar.miniframework.web.annotation.Mapping;
import com.tiddar.miniframework.web.annotation.RequestParam;
import com.tiddar.miniframework.web.enums.MapperType;
import com.tiddar.miniframework.web.exception.DispatcherException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author tiddar
 */
@SuppressWarnings("ALL")
public class Dispatcher extends HttpServlet {
    private static String basePackage = PropertiesUtil.getProperties("miniframework.api.package");
    private static Set<Class<?>> apiClasses = new HashSet<>();
    private static Map<String, Mapper> mappers = new HashMap<>();
    private static List allowSuffixs = Arrays.asList(PropertiesUtil.getProperties("miniframework.api.allowsuffix").split(","));
    private static boolean lazyLoadApis = Boolean.valueOf(PropertiesUtil.getProperties("miniframework.api.lazy") == null || PropertiesUtil.getProperties("miniframework.api.lazy") == "" ? "false" : PropertiesUtil.getProperties("miniframework.api.lazy"));
    public static boolean hasInit = false;

    public static void webInit() {
        if (apiClasses.size() == 0) {
            Set<Class<?>> classesUnderApiPackage = Utility.getClasses(basePackage);
            for (Class<?> clazz : classesUnderApiPackage) {
                if (clazz.getAnnotation(Api.class) != null) {
                    apiClasses.add(clazz);
                    String classurl = clazz.getAnnotation(Api.class).value();
                    for (Method method : clazz.getMethods()) {
                        if (method.getAnnotation(Mapping.class) != null) {
                            String methodurl = classurl + method.getAnnotation(Mapping.class).value();
                            Mapper mapper = null;
                            try {
                                mapper = new Mapper(methodurl, clazz.newInstance(), method, method.getAnnotation(Mapping.class).method(), method.getAnnotation(Mapping.class).type());
                                mappers.put(methodurl, mapper);
                                System.out.println("注册url:" + methodurl + "  对应的method:" + mapper.method);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }
        hasInit = true;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }


    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        try {
            Mapper mapper = mappers.get(uri);
            if (mapper == null) {
                DispatcherException.exception(404, "url在mini注册的路由里未找到", resp);
                return;
            }
            Object mappingReturn = invokeMapping(req, resp, mapper.apiInstance, mapper.method);
            if (mapper.mapperType == MapperType.JSON) {
                resp.getWriter().println(mappingReturn);
            } else if (mapper.mapperType == MapperType.FORWARD) {
                req.getRequestDispatcher((String) invokeMapping(req, resp, mapper.apiInstance, mapper.method)).forward(req, resp);
            } else if (mapper.mapperType == MapperType.SEND_REDIRECT) {
                resp.sendRedirect((String) invokeMapping(req, resp, mapper.apiInstance, mapper.method));
            } else if (mapper.mapperType == MapperType.SEND_REDIRECT_RELATIVE) {
                resp.sendRedirect(req.getContextPath() + (String) invokeMapping(req, resp, mapper.apiInstance, mapper.method));
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            DispatcherException.exception(405, "参数错误，堆栈：" + e.getCause(), resp);
        } catch (Exception e) {
            e.printStackTrace();
            DispatcherException.exception(500, "程序出错，堆栈：" + e.getCause(), resp);
        }
    }

    /**
     * 调用api类的mapping方法，将请求中的参数传入mapping方法，将mapping方法的返回值造型为mapping对应类型response所需的数据或者转发出去.
     *
     * @param req     请求
     * @param resp    响应
     * @param api     api对象
     * @param mapping mapping方法的Method实例
     * @return
     */
    private Object invokeMapping(HttpServletRequest req, HttpServletResponse resp, Object api, Method mapping) throws Exception {
        Class clazz = api.getClass();
        //得到该方法参数信息数组
        Parameter[] parameters = mapping.getParameters();
        Object[] mappingParameters = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getType() == HttpSession.class) {
                mappingParameters[i] = req.getSession();
                continue;
            } else if (parameter.getType() == HttpServletRequest.class) {
                mappingParameters[i] = req;
                continue;
            }
            String mappingParamName = parameter.getName();
            //mapping方法上有requestparam注解，标记参数名
            if (parameter.getAnnotation(RequestParam.class) != null) {
                mappingParamName = parameter.getAnnotation(RequestParam.class).value();
            }
            String value = req.getParameter(mappingParamName);
            //field为反射出来的字段类型
            String mappingParamterType = parameter.getType().getName();
            String mappingParamterTypeSimple = parameter.getType().getSimpleName();
            if (parameter.getType() == String.class) {
                mappingParameters[i] = value;
            } else if (mappingParamterType.indexOf("java.lang.") == 0) {
                Object param = Utility.convertStringToOtherType(parameter.getType(), value);
                mappingParameters[i] = param;
            }

//            else if (parameter.getType() == HttpResponse.class) {
//                mappingParameters.add(resp);
//            }

        }
        return mapping.invoke(api, mappingParameters);
    }


    private String getStackTraceAsString(Exception e) {
        // StringWriter将包含堆栈信息
        StringWriter stringWriter = new StringWriter();
        //必须将StringWriter封装成PrintWriter对象，
        //以满足printStackTrace的要求
        PrintWriter printWriter = new PrintWriter(stringWriter);
        //获取堆栈信息
        e.printStackTrace(printWriter);
        //转换成String，并返回该String
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }
}