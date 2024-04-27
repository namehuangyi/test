package com.bjpowernode.oa.servlet.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 获取请求路径
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") !=null ||
                "/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) ||
        "/user/login".equals(servletPath) || "/user/exit".equals(servletPath)){
            chain.doFilter(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
