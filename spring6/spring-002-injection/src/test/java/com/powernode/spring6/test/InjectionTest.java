package com.powernode.spring6.test;

import com.powernode.spring6.bean.Student;
import com.powernode.spring6.bean.User;
import com.powernode.spring6.jdbc.MyDataSource;
import com.powernode.spring6.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionTest {

    @Test
    public void testInjection4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean.xml");
        Student studentBean = applicationContext.getBean("studentBean", Student.class);
        System.out.println(studentBean);
    }

    @Test
    public void testProperties(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-properties.xml");
        MyDataSource ms = applicationContext.getBean("ms", MyDataSource.class);
        System.out.println(ms);
    }

    @Test
    public void testInjection3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        User user = applicationContext.getBean("UserBean", User.class);
        System.out.println(user);
    }

    @Test
    public void testInjection2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService2 = applicationContext.getBean("OrderServiceBean2", OrderService.class);
        orderService2.save();
    }

    @Test
    public void testInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService = applicationContext.getBean("OrderServiceBean", OrderService.class);
        orderService.save();
    }
}
