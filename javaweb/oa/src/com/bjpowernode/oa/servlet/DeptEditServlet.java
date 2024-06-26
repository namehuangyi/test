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

public class DeptEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contextPath = request.getContextPath();
        PrintWriter out = response.getWriter();
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改页面</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("<h1 align='center'>修改表单</h1>");
        out.print("<hr />");
        out.print("<form action='"+contextPath+"/dept/update' method='post'>");
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            while (rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("部门编号：<input type='text' name='deptno' value='"+deptno+"' readonly /><br />");
                out.print("部门名称：<input type='text' name='dname' value='"+dname+"' /><br />");
                out.print("部门位置：<input type='text' name='loc' value='"+loc+"' /><br />");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("		<button type='submit'>修改</button>");
        out.print("</form>");
        out.print("	</body>");
        out.print("</html>");
    }
}
