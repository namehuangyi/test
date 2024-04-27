package com.powernode.proxy.utils;

import com.powernode.proxy.service.OrderService;
import com.powernode.proxy.service.TimeInvocationHandler;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Object newProxyInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TimeInvocationHandler(target));
    }
}
