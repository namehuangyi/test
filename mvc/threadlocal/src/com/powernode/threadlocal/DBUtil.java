package com.powernode.threadlocal;

public class DBUtil {
    //静态变量特点：类加载时执行，并且只执行一次
    private static MyThreadlocal<Connection> local = new MyThreadlocal<>();
    public static Connection getConnection(){
        Connection connection = local.get();
        if (connection == null){
            connection = new Connection();
            local.set(connection);
        }
        return connection;
    }
}
