package com.powernode.threadlocal;

public class UserService {
    UserDao userDao = new UserDao();

    public void save(){
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        userDao.insert();
    }
}
