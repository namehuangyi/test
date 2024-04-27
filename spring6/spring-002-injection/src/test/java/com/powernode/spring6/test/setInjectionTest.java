package com.powernode.spring6.test;

import com.powernode.spring6.service.CustomerService;
import com.powernode.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class setInjectionTest {
    @Test
    public void testSetInjection(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("UserServiceBean", UserService.class);
        userService.saveUser();
    }

    @Test
    public void testConstructorTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        CustomerService cs = applicationContext.getBean("cs", CustomerService.class);
        cs.save();
    }
}
