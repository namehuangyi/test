package com.powernode.proxy.client;

import com.powernode.proxy.service.TimerMethodInterceptor;
import com.powernode.proxy.service.UserService;
import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        // 创建字节码增强器对象
        // 这个对象是CGLIB库的核心对象，就是依靠它来生成代理类
        Enhancer enhancer = new Enhancer();
        // 告诉CGLIB父类是谁，目标对象是谁
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new TimerMethodInterceptor());
        UserService proxyObj = (UserService) enhancer.create();
        System.out.println(proxyObj);
        boolean success = proxyObj.login("zhangsan", "123");
        System.out.println(success ? "登录成功" : "登录失败");
        proxyObj.logout();
    }
}
