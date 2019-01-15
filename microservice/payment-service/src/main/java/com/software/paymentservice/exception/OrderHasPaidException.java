package com.software.paymentservice.exception;

/**
 * 订单已经支付异常类定义
 */
public class OrderHasPaidException extends RuntimeException {
    public OrderHasPaidException(String msg){
        super(msg);
    }
}
