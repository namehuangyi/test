package com.powernode.bank.web.servlet;

import com.powernode.bank.exceptions.AppException;
import com.powernode.bank.exceptions.MoneyNotEnoughtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/transfer")
public class AccountTransforServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = Double.parseDouble(request.getParameter("money"));
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        String url = "jdbc:mysql://localhost:3306/mvc";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fromActno);
            rs = ps.executeQuery();
            if (rs.next()){
                double balance = rs.getDouble("balance");
                if (balance < money){
                    throw new MoneyNotEnoughtException("对不起，余额不足");
                }
                conn.setAutoCommit(false);
                String sql2 = "update t_act set balance = balance - ? where actno = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setDouble(1, money);
                ps2.setString(2, fromActno);
                int count = ps2.executeUpdate();

                String sql3 = "update t_act set balance = balance + ? where actno = ?";
                ps3 = conn.prepareStatement(sql3);
                ps3.setDouble(1, money);
                ps3.setString(2, toActno);
                count += ps3.executeUpdate();

                if (count !=2){
                    throw new AppException("App异常，请联系系统管理员");
                } else {
                    conn.commit();
                    out.print("转账成功");
                }
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            out.print(e.getMessage());
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
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if (ps3 != null) {
                try {
                    ps3.close();
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
    }
}
