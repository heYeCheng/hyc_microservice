package com.software.orderservice.exception;

/**
 * 库存不足异常类定义
 */
public class StockCountNotEnoughException extends RuntimeException {
    public StockCountNotEnoughException(String msg){
        super(msg);
    }
}
