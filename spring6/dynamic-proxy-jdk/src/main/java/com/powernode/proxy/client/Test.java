package com.powernode.proxy.client;

import com.powernode.proxy.service.OrderService;
import com.powernode.proxy.service.OrderServiceImpl;
import com.powernode.proxy.service.TimeInvocationHandler;
import com.powernode.proxy.utils.ProxyUtil;

import java.lang.reflect.Proxy;


public class Test {
    public static void main(String[] args) {
        OrderService target = new OrderServiceImpl();
        // OrderService proxy = (OrderService)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimeInvocationHandler(target));
        OrderService proxy = (OrderService) ProxyUtil.newProxyInstance(target);
        proxy.generate();
        proxy.modify();
        proxy.detail();
        String name = proxy.getName();
        System.out.println(name);
    }
}
