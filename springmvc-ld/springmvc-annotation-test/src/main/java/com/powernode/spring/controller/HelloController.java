package com.powernode.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("HelloController's hello");
        return "/hello";
    }
}
