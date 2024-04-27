package com.powernode.bank.service.impl;

import com.powernode.bank.dao.impl.AccountDaoImpl;
import com.powernode.bank.exceptions.AppException;
import com.powernode.bank.exceptions.MoneyNotEnoughException;
import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.service.AccountService;
import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {

        // 资源自动管理（放到try里面,conn自动关闭）
        try (Connection conn = DBUtil.getConnection()){
            conn.setAutoCommit(false);

            Account fromAct = accountDao.selectByActno(fromActno);
            if (fromAct.getBalance() < money){
                throw new MoneyNotEnoughException("对不起，余额不足");
            }
            Account toAct = accountDao.selectByActno(toActno);
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            int count = accountDao.update(fromAct);

            count += accountDao.update(toAct);
            if (count != 2){
                throw new AppException("账号转账异常！！！");
            }

            conn.commit();
        } catch (SQLException e) {
            throw new AppException("账号转账异常！！！");
        }
    }
}
