package com.software.orderservice.service.impl;

import com.software.commonservice.common.ResultData;
import com.software.commonservice.common.constants.PubConstant;
import com.software.commonservice.common.dto.OrderDto;
import com.software.orderservice.entity.Order;
import com.software.orderservice.exception.StockCountNotEnoughException;
import com.software.orderservice.repository.OrderRepository;
import com.software.orderservice.service.OrderService;
import com.software.orderservice.service.feign.CommodityFeignService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderDao;

    @Autowired
    private CommodityFeignService commodityFeignService;

    @Override
    public Order createOrder(Integer userId, Integer commodityId, Integer purchaseCount) {
        ResultData resultData = commodityFeignService.getCommodityCountById(commodityId);

        if(resultData != null && resultData.getBo() != null){
            Object commodityCount = resultData.getBo();

            if(commodityCount instanceof Integer){
                int commodityCnt = (Integer)commodityCount;

                if(commodityCnt >= purchaseCount){
                    //创建订单信息记录
                    Order order = createOrderRecord(userId, commodityId, purchaseCount);
                    //扣减库存
                    commodityFeignService.updateStockByCommodityId(commodityId,-purchaseCount);

                    return order;
                }
                else {
                    throw new StockCountNotEnoughException("商品库存不足");
                }
            }
        }

        return null;
    }

    @Transactional
    @Override
    public void updateOrderStatusById(Integer orderId, int payStatus) {
        orderDao.updateOrderStatusById(orderId,payStatus);
    }

    @Override
    public OrderDto getOrderInfoById(Integer orderId) {
        Order order = orderDao.findByOrderId(orderId);

        if(order != null){
            OrderDto orderDto = new OrderDto();

            BeanUtils.copyProperties(order,orderDto);

            return orderDto;
        }

        return null;
    }

    private Order createOrderRecord(Integer userId, Integer commodityId, Integer purchaseCount) {
        Order order = new Order();

        order.setCommodityId(commodityId);
        order.setPurchaseCount(purchaseCount);
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setPayStatus(PubConstant.ORDER_WAIT_FOR_PAID);

        return orderDao.save(order);
    }
}
