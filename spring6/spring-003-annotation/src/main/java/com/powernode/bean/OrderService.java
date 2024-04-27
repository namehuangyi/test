package com.powernode.bean;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Resource(name = "orderImpl")
    private Order order;
    public void add(){
        order.add();
    }
}
