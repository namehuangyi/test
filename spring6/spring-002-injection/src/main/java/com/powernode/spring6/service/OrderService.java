package com.powernode.spring6.service;

import com.powernode.spring6.Dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(){
        orderDao.insert();
    }
}
