## miniframework
是一个仿照springmvc的api实现的简单的web框架哦
使用教程:
1. 配置mini:在resources目录下建立mini.properties文件
配置miniframework.api.package----api扫描包（类比springmvc的controller)<br>
与miniframework.api.allowsuffix(限制通过mini的请求uri后缀，比如配置了.do,.action则只有无后缀url与.do,.action会被miniframework处理)
2. 编写miniapi:在上述的api扫描包下建立api类，需要在类上添加API(@Api)注解,并且需要在对应的请求处理的映射方法添加Mapping注解（@Mapping)
          注：
    - API注解只有一个value参数，指此类处理的的url
    - Mapping注解有三个参数，指此方法处理的的url，处理的请求的HTTP方法，**请求类型**
        - 其中请求类型包括 JSON,FORWARD,SEND_REDIRECT,SEND_REDIRECT_RELATIVE;分别对应json api,请求转发，请求重定向，与带项目根路径的请求重定向.
        当请求类型为请求转发获取请求重定向时必须返回String类型作为request.getRequestDispatcher()的参数或response.sendRedirect()的参数。