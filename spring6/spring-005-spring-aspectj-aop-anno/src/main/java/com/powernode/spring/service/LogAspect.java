package com.powernode.spring.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("logAspect")
@Aspect
public class LogAspect {
    @Before("execution(* com.powernode.spring.service.UserService.*(..))")
    public void 增强(){
        System.out.println("我是一个通知，我是一段增强代码...");
    }
}
