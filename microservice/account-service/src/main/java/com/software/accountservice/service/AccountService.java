package com.software.accountservice.service;


import java.math.BigInteger;

/**
 * 用户账户服务层接口定义
 */
public interface AccountService {
    /**
     * 根据用户id获取账户余额
     * @param userId
     * @return
     */
    BigInteger getUserBalanceByUserId(Integer userId);

    /**
     * 根据用户id，增加用户余额
     * @param userId
     * @param amount
     */
    void updateUserBalanceByUserId(Integer userId,BigInteger amount);
}
