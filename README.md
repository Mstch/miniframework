## miniframework
#### web模块：是一个仿照springmvc的api实现的简单的web框架哦
使用教程:
1. 配置mini:在resources目录下建立mini.properties文件
配置miniframework.api.package----api扫描包（类比springmvc的controller)<br>   
与miniframework.api.allowsuffix(限制通过mini的请求uri后缀，比如配置了.do,.action则只有无后缀url与.do,.action会被miniframework处理)
2. 编写miniapi:在上述的api扫描包下建立api类，需要在类上添加API(@Api)注解,并且需要在对应的请求处理的映射方法添加Mapping注解（@Mapping)
          注：
    - API注解只有一个value参数，指此类处理的的url
    - Mapping注解有三个参数，value指此方法处理的的url，method指处理的请求的HTTP方法，type指**请求类型**
        - 其中请求类型包括 JSON(默认值，直接写入response),FORWARD(请求转发),SEND_REDIRECT（请求重定向）,SEND_REDIRECT_RELATIVE;分别对应json api（当然相应里也可直接写普通字符串）,请求转发，请求重定向，与带项目根路径的请求重定向.
        当请求类型为请求转发获取请求重定向时必须返回String类型作为request.getRequestDispatcher()的参数或response.sendRedirect()的参数。
    - Mapping方法的编写   
        **这里的参数a若想直接注入，需要在编译时加上-parameters参数，具体怎样加看这里[Java8编译器的新特性-参数名字保留在字节码中](http://xujin.org/ex/jdk8-parameters/)**
        <br>**若不想添加这个参数，则需要在每个mapping方法的参数前加上@RequestParam注解，注解的值是请求的参数名**
        - 加上上述的mapping注解后,可书编写mapping方法，其中的参数去匹配request里的参数名以及request本身，还可以修改此次请求的响应response。举例：
           ```java
              @Api("/test")
              public class Test {         
                  @Mapping("/ss")
                  public String sss(Integer a){//这里也可以写成@RequestParam("a")
                      System.out.println(a);
                      return "1221";
                  }
              }

            ```
            > 此方法将会把1221作为字符串直接写入response
       
        当然也可以在方法里接受request,session两种对象，mini将会把这三种对象自动传给这个方法
        像这样：
        ```java
            @Api("/test")
            public class Test {      
                @Mapping("/sss")
                public String sss(Integer a, HttpServletRequest request, HttpSession session) {
                    request.setAttribute("fuck", 2);
                    session.setAttribute("fuck", Integer.valueOf(session.getAttribute("fuck").toString()) + 1);
                    System.out.println(a + "" + request.getAttribute("fuck"));
                    return request.getAttribute("fuck").toString();
                }
            }
        ``` 
        或者加上请求转发 ：
        ```java
        @Api("/test")
        public class Test {
        
        
            @Mapping("/ss")
            public String ss(Integer a, HttpServletRequest req, HttpSession session) {
                return req.getAttribute("fuck").toString();
            }
        
            @Mapping(value = "/sss", type = MapperType.FORWARD)
            public String sss(Integer a, HttpServletRequest request, HttpSession session) {
                request.setAttribute("fuck",123);
                return "/test/ss";
            }
        }
        ```
#### orm模块：将最基础的sql封装哦
   使用教程：
   
