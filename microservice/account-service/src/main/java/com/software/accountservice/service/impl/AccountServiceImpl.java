package com.software.accountservice.service.impl;

import com.software.accountservice.entity.UserAccount;
import com.software.accountservice.repository.UserAccountRepository;
import com.software.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserAccountRepository userAccountDao;

    @Override
    public BigInteger getUserBalanceByUserId(Integer userId) {
        UserAccount userAccount = userAccountDao.findByUserId(userId);

        if(userAccount != null){
            return userAccount.getAccBalance();
        }

        return null;
    }

    @Override
    @Transactional
    public void updateUserBalanceByUserId(Integer userId, BigInteger amount) {
        userAccountDao.updateUserBalanceWithUserId(userId,amount);
    }
}
