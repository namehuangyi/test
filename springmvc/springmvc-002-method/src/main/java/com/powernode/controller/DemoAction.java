package com.powernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoAction {

    @RequestMapping(value = "/demo.action", method = RequestMethod.GET)
    public String demo(){
        System.out.println("GET请求");
        return "main";
    }

    @RequestMapping(value = "/demo.action", method = RequestMethod.POST)
    public String demo1(){
        System.out.println("POST请求");
        return "main";
    }
}
