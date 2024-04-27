package com.powernode.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String username = bundle.getString("username");
    private static String password = bundle.getString("password");

    private static ThreadLocal<Connection> local = new ThreadLocal<>();
    // 不让创建对象，因为工具类中的方法都是静态的，不需要创建对象。
    // 为了防止创建对象，故将构造方法私有化
    private DBUtil() {
    }

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if (conn == null){
            conn = DriverManager.getConnection(url, username, password);
            local.set(conn);
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (conn != null) {
            try {
                conn.close();
                // 思考一下，为什么conn关闭之后，这里要从大Map中移除呢？
                // 根本原因是：Tomcat服务器是支持线程池的。也就是说一个人用过了t1线程，t1线程还有可能被其他用户使用
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
