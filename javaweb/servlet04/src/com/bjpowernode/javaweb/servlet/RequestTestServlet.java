package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getParameterMap()
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String key = it.next();
            String[] values = parameterMap.get(key);
            /*System.out.println(key);
            System.out.println(values);*/
            for (String value : values){
                System.out.println(key + "=" + value);
            }
        }

        // request.getParameterNames()
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            // request.getParameterValues(name)
            String[] values = request.getParameterValues(name);
            for (String value : values){
                System.out.println(name + "=" + value);
            }
        }

        //
        String username = request.getParameter("username");
        System.out.println(username);
        String userpwd = request.getParameter("userpwd");
        System.out.println(userpwd);
        String interest = request.getParameter("interest");
        System.out.println(interest);
        ServletContext servletContext = this.getServletContext();
    }
}
