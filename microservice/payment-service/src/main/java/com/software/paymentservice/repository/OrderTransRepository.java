package com.software.paymentservice.repository;

import com.software.paymentservice.entity.OrderTransInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单交易持久层接口定义
 */
public interface OrderTransRepository extends JpaRepository<OrderTransInfo,Integer> {
}
