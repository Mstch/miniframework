package com.tiddar.miniframework.web.filter;

import com.tiddar.miniframework.web.Dispatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharSetFilter", urlPatterns = "/*")
public class CharSetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if (!Dispatcher.hasInit) {
            resp.getWriter().println("核心类尚未初始化,请稍等");
        } else chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
