package com.powernode.threadlocal;

public class test {

    public static void main(String[] args) {
        UserService userService = new UserService();
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        userService.save();
    }
}
