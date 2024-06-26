package com.bjpowernode.oa.servlet.web.action;

import com.bjpowernode.oa.servlet.bean.User;
import com.bjpowernode.oa.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)){
            doLogin(request, response);
        }else if ("/user/exit".equals(servletPath)){
            doExit(request, response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            // 销毁session
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        if (success == true){
            HttpSession session = request.getSession();
            // session.setAttribute("username", username);
            User user = new User("username", password);
            session.setAttribute("user", user);
            String f = request.getParameter("f");
            if ("1".equals(f)){
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置cookie有效时间
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                // 设置cookie的路径（只要访问这个路径，浏览器就一定会携带这两个cookie）
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                // 响应cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
