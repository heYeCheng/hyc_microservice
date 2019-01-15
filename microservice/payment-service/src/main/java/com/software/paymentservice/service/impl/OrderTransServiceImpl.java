package com.software.paymentservice.service.impl;

import com.software.commonservice.common.ResultData;
import com.software.commonservice.common.constants.PubConstant;
import com.software.commonservice.common.dto.OrderDto;
import com.software.paymentservice.entity.OrderTransInfo;
import com.software.paymentservice.exception.AccountBalanceInsufficientException;
import com.software.paymentservice.exception.OrderHasPaidException;
import com.software.paymentservice.repository.OrderTransRepository;
import com.software.paymentservice.service.OrderTransService;
import com.software.paymentservice.service.feign.AccountFeignService;
import com.software.paymentservice.service.feign.CommodityFeignService;
import com.software.paymentservice.service.feign.OrderFeignService;
import com.software.paymentservice.utils.DataConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * 订单交易服务层接口实现类
 */
@Service("orderTransService")
public class OrderTransServiceImpl implements OrderTransService {
    @Autowired
    private OrderTransRepository orderTransDao;
    @Autowired
    private OrderFeignService orderFeignService;
    @Autowired
    private AccountFeignService accountFeignService;
    @Autowired
    private CommodityFeignService commodityFeignService;

    @Override
    public OrderTransInfo makePayment(Integer orderId, Integer userId) {
        OrderDto orderDto = getOrderDtoByOrderId(orderId);
        BigInteger purchaseTotalAmount = BigInteger.ZERO;

        if (orderDto != null) {
            if(orderDto.getPayStatus() == PubConstant.ORDER_HAS_PAID){
                throw new OrderHasPaidException("订单已经支付");
            }

            BigInteger commodityPrice = getCommodityPriceByCommodityId(orderDto.getCommodityId());
            //计算购买商品总金额
            purchaseTotalAmount = commodityPrice.multiply(new BigInteger(orderDto.getPurchaseCount() + ""));
        }

        //获取用户账户余额
        BigInteger accountBalance = getAccountBalanceByUserId(userId);
        if (accountBalance.compareTo(purchaseTotalAmount) < 0) {
            throw new AccountBalanceInsufficientException("用户账户余额不足");
        }

        //扣除用户账户余额
        accountFeignService.updateUserAccBalanceByUserId(userId, purchaseTotalAmount.negate());

        //修改订单状态
        orderFeignService.updateOrderStatus(orderId, PubConstant.ORDER_HAS_PAID);

        //创建订单流水
        OrderTransInfo orderTransInfo = createOrderTransInfo(orderId, purchaseTotalAmount);

        return orderTransInfo;
    }

    /**
     * 创建订单流水
     *
     * @param orderId
     * @param purchaseTotalAmount
     */
    private OrderTransInfo createOrderTransInfo(Integer orderId, BigInteger purchaseTotalAmount) {
        OrderTransInfo orderTransInfo = new OrderTransInfo();

        orderTransInfo.setOrderId(orderId);
        orderTransInfo.setTransCount(purchaseTotalAmount);
        orderTransInfo.setCreateTime(new Date());

        return orderTransDao.save(orderTransInfo);
    }


    /**
     * 根据订单id获取订单信息
     *
     * @param orderId
     * @return
     */
    private OrderDto getOrderDtoByOrderId(Integer orderId) {
        ResultData orderResultData = orderFeignService.getOrderInfoByOrderId(orderId);

        if (orderResultData != null) {
            Object commodityCountObj = orderResultData.getBo();

            if (commodityCountObj instanceof Map) {
                try {
                    return (OrderDto) DataConverterUtil.convertMapToBean(OrderDto.class,
                            (Map) commodityCountObj);
                } catch (Exception e) {
                    System.out.println("数据转换异常！");
                }
            }
        }

        return null;
    }

    /**
     * 根据商品id获取商品价格
     *
     * @param commodityId
     * @return
     */
    private BigInteger getCommodityPriceByCommodityId(Integer commodityId) {
        BigInteger commodityPrice = BigInteger.ZERO;

        ResultData commodityResultData = commodityFeignService.getCommodityPriceById(commodityId);

        if (commodityResultData != null) {
            Object commodityCountObj = commodityResultData.getBo();

            if (commodityCountObj instanceof Integer) {
                return new BigInteger(commodityCountObj + "");
            }
        }

        return commodityPrice;
    }

    /**
     * 根据用户id获取用户账户余额
     *
     * @param userId
     * @return
     */
    private BigInteger getAccountBalanceByUserId(Integer userId) {
        BigInteger accountBalance = BigInteger.ZERO;

        ResultData accountResultData = accountFeignService.getAccountBalanceByUserId(userId);

        if (accountResultData != null) {
            Object accountBalanceObj = accountResultData.getBo();

            if (accountBalanceObj instanceof Integer) {
                return new BigInteger(accountBalanceObj + "");
            }
        }

        return accountBalance;
    }
}
