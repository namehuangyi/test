package com.powernode.proxy.service;

public class OrderServiceImpl implements OrderService{
    @Override
    public String getName() {
        System.out.println("获取名字");
        return "小明";
    }

    @Override
    public void generate() {
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生成订单");
    }

    @Override
    public void modify() {
        try {
            Thread.sleep(56);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("修改订单");
    }

    @Override
    public void detail() {
        try {
            Thread.sleep(78);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查询订单");
    }
}
