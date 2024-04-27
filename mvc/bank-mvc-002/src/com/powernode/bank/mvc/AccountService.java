package com.powernode.bank.mvc;

import com.powernode.bank.Exception.AppException;
import com.powernode.bank.Exception.MoneyNotEnoughException;

public class AccountService {

    private AccountDao accountDao = new AccountDao();
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
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
    }
}
