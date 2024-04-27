package com.powernode.threadlocal;

public class UserDao {
    public void insert(){
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }
}
