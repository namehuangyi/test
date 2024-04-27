package com.powernode.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.powernode.pojo.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ajaxrequest4")
public class AjaxRequest4Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String jsonstr = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax", "root", "123456");
            String sql = "select * from t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String addr = rs.getString("addr");
                Student student = new Student(name, age, addr);
                studentList.add(student);
            }
            // 将List集合转换成json格式的字符串
            jsonstr = JSON.toJSONString(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        out.print(jsonstr);
    }
}
