package com.software.paymentservice.exception;


/**
 * 用户账户余额不足异常类定义
 */
public class AccountBalanceInsufficientException extends RuntimeException {
    public AccountBalanceInsufficientException(String msg){
        super(msg);
    }
}
