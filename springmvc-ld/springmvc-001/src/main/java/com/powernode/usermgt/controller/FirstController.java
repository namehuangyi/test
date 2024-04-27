package com.powernode.usermgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {
    @RequestMapping("/test")
    public String hehe(){
        // 返回一个逻辑视图名称
        return "first";
    }

}
