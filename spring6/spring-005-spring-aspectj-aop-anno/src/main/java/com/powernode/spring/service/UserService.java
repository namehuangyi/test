package com.powernode.spring.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public void login(){
        System.out.println("系统正在进行身份认证！");
    }
}
