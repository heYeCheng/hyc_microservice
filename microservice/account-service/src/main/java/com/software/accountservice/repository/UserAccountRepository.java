package com.software.accountservice.repository;

import com.software.accountservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;


/**
 * 用户账户持久层接口定义
 */
public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    /**
     * 根据用户id获取用户账户
     * @param userId
     * @return
     */
    UserAccount findByUserId(Integer userId);

    /**
     * 根据用户id更新用户账户余额
     * @param userId
     * @param balance
     */
    @Query(value = "update tbl_user_acc set acc_balance = acc_balance + ?2 where user_id = ?1 ",nativeQuery = true)
    @Modifying
    Integer updateUserBalanceWithUserId(Integer userId, BigInteger balance);
}
