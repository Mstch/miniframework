package com.tiddar.miniframework.web.exception;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherException{
    public static void exception(int status, String reason, HttpServletResponse response){
        response.setStatus(status);
        response.setContentType("text/html; charset=UTF-8");
        try {
            response.getWriter().println("<h1>http status:"+status+"</h1>");
            response.getWriter().println("<hr>");
            response.getWriter().println("<h3>reason:"+reason+"</h3>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
