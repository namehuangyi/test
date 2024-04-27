package com.powernode.annotation.test;

import com.powernode.annotation.Component;
import com.powernode.bean.Config1;
import com.powernode.bean.OrderService;
import com.powernode.bean.User;
import com.powernode.bean.Vip;
import com.powernode.bean1.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnnotationTest {

    @Test
    public void testAnnotation3(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config1.class);
        OrderService orderService = annotationConfigApplicationContext.getBean("orderService", OrderService.class);
        orderService.add();
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.add();*/
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        studentService.deleteStudent();*/
    }

    @Test
    public void testAnnotation2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        Vip vip = applicationContext.getBean("vip", Vip.class);
        System.out.println(vip);
        System.out.println(userBean);
    }

    @Test
    public void testAnnotation1() throws Exception{
        Map<String, Object> map = new HashMap<>();
        String packageName = "com.powernode.bean";
        String packagePath = packageName.replaceAll("\\.", "/");
        // url:指定资源的位置
        URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
        // 获取绝对路径
        String path = url.getPath();
        File file = new File(path);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            try{
                // System.out.println(f.getName());
                String className = packageName + "." +f.getName().split("\\.")[0];
                // System.out.println(className);
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Component.class)) {
                    Component annotation = aClass.getAnnotation(Component.class);
                    String id = annotation.value();
                    Object User = aClass.newInstance();
                    map.put(id, User);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.out.println(map);
    }

    @Test
    public void testAnnotation() throws Exception{
        Class<?> aClass = Class.forName("com.powernode.bean.User");
        if (aClass.isAnnotationPresent(Component.class)) {
            Component annotation = aClass.getAnnotation(Component.class);
            System.out.println(annotation.value());
        }
    }
}
