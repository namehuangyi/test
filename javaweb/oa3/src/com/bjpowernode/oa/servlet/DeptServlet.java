package com.bjpowernode.oa.servlet;

import com.bjpowernode.oa.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/ins", "/dept/del", "/dept/update", "/dept/edit", "/dept/list", "/dept/select"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)){
            doList(request, response);
        } else if ("/dept/ins".equals(servletPath)){
            doIns(request, response);
        } else if ("/dept/del".equals(servletPath)){
            doDel(request, response);
        } else if ("/dept/update".equals(servletPath)){
            doUpdate(request, response);
        } else if ("/dept/edit".equals(servletPath)){
            doEdit(request, response);
        } else if ("/dept/select".equals(servletPath)){
            doSelect(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门列表页面</title>");
        out.print("<script type='text/javascript'>");
        out.print("    function del(dno){");
        out.print("        if(window.confirm('亲，删了不可恢复哦！')){");
        out.print("            document.location.href='"+contextPath+"/dept/del?deptno=' + dno");
        out.print("        }");
        out.print("    }");
        out.print("</script>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1 align='center'>部门列表</h1>");
        out.print("		<hr />");
        out.print("		<table border='1px' align='center' width='50%'>");
        out.print("			<tr>");
        out.print("				<th>序号</th>");
        out.print("				<th>部门编号</th>");
        out.print("				<th>部门名称</th>");
        out.print("				<th>操作</th>");
        out.print("			</tr>");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                out.print("			<tr>");
                out.print("				<td>"+(++i)+"</td>");
                out.print("				<td>"+deptno+"</td>");
                out.print("				<td>"+dname+"</td>");
                out.print("				<td>");
                out.print("					<a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("					<a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
                out.print("					<a href='"+contextPath+"/dept/select?deptno="+deptno+"'>详情</a>");
                out.print("				</td>");
                out.print("			</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        out.print("        		</table>");
        out.print("		<hr />");
        out.print("		<a href='"+contextPath+"/add.html'>新增部门</a>");
        out.print("	</body>");
        out.print("</html>");
    }

    private void doIns(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // PrintWriter out = response.getWriter();
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        String contextPath = request.getContextPath();
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;


        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
        if (count == 1){
            // request.getRequestDispatcher("/dept/list").forward(request, response);
            // 这里最好使用重定向
            response.sendRedirect(contextPath + "/dept/list");
        }else {
            // request.getRequestDispatcher("error.html").forward(request, response);
            response.sendRedirect(contextPath + "/error.html");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        String deptno = request.getParameter("deptno");
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, null);
        }
        if (count == 1) {
            request.getRequestDispatcher("/dept/list").forward(request, response);
        }else {
            request.getRequestDispatcher("error.html").forward(request, response);
        }
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, null);
        }
        if(count == 1){
            response.sendRedirect(request.getContextPath() + "/dept/list");
            // request.getRequestDispatcher("/dept/list").forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + "/error.html");
            // request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    private void doSelect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
