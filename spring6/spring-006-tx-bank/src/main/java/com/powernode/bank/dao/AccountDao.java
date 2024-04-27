package com.powernode.bank.dao;

import com.powernode.bank.pojo.Account;

public interface AccountDao {
    Account selectByActno(String actno);

    int update(Account act);
}
