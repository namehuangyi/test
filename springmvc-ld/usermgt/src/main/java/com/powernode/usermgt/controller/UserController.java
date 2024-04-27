package com.powernode.usermgt.controller;

import com.powernode.usermgt.Bean.User;
import com.powernode.usermgt.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model){
        List<User> users = userDao.selectAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(User user){
        userDao.insert(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        User user = userDao.selectById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String modify(User user){
        userDao.update(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") Integer id){
        userDao.delete(id);
        return "redirect:/user";
    }
}
