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

@WebServlet("/dataDiv")
public class AjaxAutoCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String keywords = request.getParameter("keywords");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax", "root", "123456");
            String sql = "select content from t_auto where content like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keywords + "%");
            rs = ps.executeQuery();
            // [{"content":"javascript"},{"content":"java"}]
            while (rs.next()){
                String content = rs.getString("content");
                sb.append("{\"content\":\""+content+"\"},");
            }
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
        out.print(sb.substring(0, sb.length() - 1) + "]");
    }
}
