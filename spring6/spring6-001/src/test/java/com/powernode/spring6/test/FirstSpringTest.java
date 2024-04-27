package com.powernode.spring6.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {
    @Test
    public void testFirstSpring(){
        // 第一步：获取sprng容器
        // ApplicationContext是一个接口，接口中有很多实现类，其中有一个叫做ClassPathXmlApplicationContext
        // ClassPathXmlApplicationContext是一个专门从类的根路径下加载spring配置文件的一个上下文对象
        // 这行代码只要执行：就相当于启动了spring容器，解析spring.xml文件，并且实例化所有的bean对象，放到spring容器当中
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 第二步：根据bean的id从spring容器中获取这个对象
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        // 自己怎么使用log4j2记录日志信息呢
        // 第一步：创建日志记录器对象
        Logger logger = LoggerFactory.getLogger("FirstSpringTest.class");
        // 第二步：记录日志，根据不同的级别来输出日志
        logger.info("我是一条消息");
    }
}
