package com.powernode.bean;

import org.springframework.stereotype.Component;

@Component
public class OrderImpl implements Order{
    @Override
    public void add() {
        System.out.println("加菜！！！");
    }
}
