package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoAction {

    @RequestMapping("/demo.action")
    public String demo(){
        System.out.println("服务器被访问到了");
        return "main";
    }
}
