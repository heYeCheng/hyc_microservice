package com.software.orderservice.repository;

import com.software.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 订单持久层接口定义
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {

    /**
     * 修改订单状态
     * @param orderId
     * @param payStatus
     */
    @Modifying
    @Query(value = "update tbl_order_info set pay_status = ?2 where order_id = ?1",nativeQuery = true)
    void updateOrderStatusById(Integer orderId,int payStatus);

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    Order findByOrderId(Integer orderId);
}
