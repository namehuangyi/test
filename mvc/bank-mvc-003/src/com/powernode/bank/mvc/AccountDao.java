package com.powernode.bank.mvc;

import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public int insert(Account act, Connection conn){
        PreparedStatement ps = null;
        int count = 0;
        try {
            String sql = "insert into t_act(actno, balance) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2, act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }
    int deleteByActno(String actno, Connection conn){
        PreparedStatement ps = null;
        int count = 0;
        try {
            String sql = "delete from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }
    int update(Account act, Connection conn){
        PreparedStatement ps = null;
        int count = 0;
        try {
            String sql = "update t_act set actno = ?, balance = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2, act.getBalance());
            ps.setLong(3, act.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }
    Account selectByActno(String actno, Connection conn){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            String sql = "select id, balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            rs = ps.executeQuery();
            if (rs.next()){
                long id = rs.getLong("id");
                double balance = rs.getDouble("balance");
                account = new Account();
                account.setId(id);
                account.setActno(actno);
                account.setBalance(balance);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return account;
    }
    List<Account> selectAll(Connection conn){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> list = new ArrayList<>();
        Account account = null;
        try {
            String sql = "select id, actno, balance from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String actno = rs.getString("actno");
                double balance = rs.getDouble("balance");
                account = new Account(id, actno, balance);
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
