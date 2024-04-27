package com.bjpowernode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DateAction {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    /*// 全局日期注入
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sf, true));
    }

    @RequestMapping("/date.action")
    public String date(Date mydate){
        System.out.println(sf.format(mydate));
        return "main";
    }*/

    @RequestMapping("/date.action")
    public String date(HttpServletRequest request) throws ParseException {
        List<User> list = new ArrayList<>();
        User user1 = new User("张三", sf.parse("2002-02-06"));
        User user2 = new User("李四", sf.parse("2003-02-06"));
        User user3 = new User("王五", sf.parse("2004-02-06"));
        list.add(user1);
        list.add(user2);
        list.add(user3);
        request.setAttribute("list", list);
        return "main";
    }
}
