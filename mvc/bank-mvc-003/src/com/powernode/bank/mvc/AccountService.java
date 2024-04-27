package com.powernode.bank.mvc;

import com.powernode.bank.Exception.AppException;
import com.powernode.bank.Exception.MoneyNotEnoughException;
import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao = new AccountDao();
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {

        // 资源自动管理（放到try里面）
        try (Connection conn = DBUtil.getConnection()){
            conn.setAutoCommit(false);

            Account fromAct = accountDao.selectByActno(fromActno, conn);
            if (fromAct.getBalance() < money){
                throw new MoneyNotEnoughException("对不起，余额不足");
            }
            Account toAct = accountDao.selectByActno(toActno, conn);
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            int count = accountDao.update(fromAct, conn);

            String s = null;
            s.toString();

            count += accountDao.update(toAct, conn);
            if (count != 2){
                throw new AppException("账号转账异常！！！");
            }

            conn.commit();
        } catch (SQLException e) {
            throw new AppException("账号转账异常！！！");
        }
    }
}
