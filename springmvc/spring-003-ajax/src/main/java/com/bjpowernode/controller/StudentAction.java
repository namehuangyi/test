package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentAction {

    @RequestMapping("/list.action")
    @ResponseBody // 用来解析ajax请求
    public List<Student> list(){
        List<Student> list = new ArrayList<Student>();
        Student student1 = new Student("张三", 20);
        Student student2 = new Student("李四", 30);
        Student student3 = new Student("王五", 40);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return list;
    }
}
