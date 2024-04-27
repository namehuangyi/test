package com.bjpowernode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {
    @RequestMapping("/showLogin")
    public String login(String name, String password, HttpServletRequest request){
        if ("zhangsan".equalsIgnoreCase(name) && "123".equalsIgnoreCase(password)) {
            return "show";
        }else {
            request.setAttribute("msg", "用户名或密码不正确！");
            return "index";
        }
    }
}
