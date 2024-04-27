package com.powernode.bean1;

import org.springframework.stereotype.Repository;

@Repository("studentImp")
public class StudentImp implements StudentDao{
    @Override
    public void deleteById() {
        System.out.println("删除学生信息！！！");
    }
}
