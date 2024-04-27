package com.bjpowernode.controller;

import java.util.Date;

public class User {
    private String name;
    private Date birth;

    @Override
    public String toString() {
        return "UserAction{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User(String name, Date birth) {
        this.name = name;
        this.birth = birth;
    }

    public User() {
    }
}
