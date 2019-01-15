package com.software.orderservice.service;

import com.software.commonservice.common.dto.OrderDto;
import com.software.orderservice.entity.Order;

/**
 * 订单服务层接口定义
 */
public interface OrderService {
    /**
     * 创建订单
     * @param userId
     * @param commodityId
     * @param purchaseCount
     */
    Order createOrder(Integer userId, Integer commodityId, Integer purchaseCount);


    /**
     * 修改订单状态
     * @param orderId
     * @param payStatus
     */
    void updateOrderStatusById(Integer orderId,int payStatus);


    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    OrderDto getOrderInfoById(Integer orderId);
}
