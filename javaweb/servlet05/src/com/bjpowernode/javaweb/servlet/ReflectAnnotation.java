package com.bjpowernode.javaweb.servlet;


import jakarta.servlet.annotation.WebServlet;

public class ReflectAnnotation {
    public static void main(String[] args) throws Exception{
        Class<?> welcomeServletClass = Class.forName("com.bjpowernode.javaweb.servlet.WelcomeServlet");
        if (welcomeServletClass.isAnnotationPresent(WebServlet.class)){
            WebServlet welcomeAnnotation = welcomeServletClass.getAnnotation(WebServlet.class);
            String[] value = welcomeAnnotation.value();
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }
        }
    }
}
