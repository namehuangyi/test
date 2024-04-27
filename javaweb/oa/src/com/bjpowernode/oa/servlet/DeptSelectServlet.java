package com.bjpowernode.oa.servlet;

import com.bjpowernode.oa.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门详细信息</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("<h1 align='center'>部门详细信息</h1>");
        out.print("<hr />");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            while (rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("<h1>部门编号："+deptno+"</h1>");
                out.print("<h1>部门名称："+dname+"</h1>");
                out.print("<h1>部门位置："+loc+"</h1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("<button onclick='window.history.back()'>后退</button>");
        out.print("	</body>");
        out.print("</html>");
    }
}
