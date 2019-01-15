package com.software.paymentservice.service;

import com.software.paymentservice.entity.OrderTransInfo;

/**
 * 订单交易服务层接口定义
 */
public interface OrderTransService {
    /**
     * 支付订单
     * @param orderId
     * @param userId
     * @return
     */
    OrderTransInfo makePayment(Integer orderId,Integer userId);
}
