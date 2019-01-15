package com.software.orderservice.controller;

import com.software.commonservice.common.ResultData;
import com.software.orderservice.exception.StockCountNotEnoughException;
import com.software.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public ResultData createOrder(@RequestParam(name = "userId", required = true) Integer userId,
                                  @RequestParam(name = "commodityId", required = true) Integer commodityId,
                                  @RequestParam(name = "purchaseCount", required = true) Integer purchaseCount) throws Exception {
        ResultData resultData = new ResultData();
        resultData.setStatus(ResultData.Status.error);

        if (commodityId == 0 || purchaseCount == 0) {
            resultData.setMessage("参数传入有误!");
        } else {
            try {
                resultData.setBo(orderService.createOrder(userId, commodityId, purchaseCount));
                resultData.setStatus(ResultData.Status.success);
            } catch (StockCountNotEnoughException e) {
                resultData.setMessage(e.getMessage());
            } catch (Exception e) {
                resultData.setMessage("调用订单服务异常");
            }
        }

        return resultData;
    }

    /**
     * 修该订单状态
     *
     * @param orderId
     * @param payStatus
     * @return
     */
    @RequestMapping("/updateOrderStatus")
    public ResultData updateOrderStatus(Integer orderId, Integer payStatus) {
        ResultData resultData = new ResultData();

        try {
            orderService.updateOrderStatusById(orderId, payStatus);
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用订单服务异常");
        }

        return resultData;
    }

    @RequestMapping("/getOrderInfoByOrderId")
    public ResultData getOrderInfoByOrderId(Integer orderId) {
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(orderService.getOrderInfoById(orderId));
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用订单服务异常");
        }

        return resultData;
    }
}
