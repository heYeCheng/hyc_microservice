package com.software.paymentservice.controller;

import com.software.commonservice.common.ResultData;
import com.software.paymentservice.exception.AccountBalanceInsufficientException;
import com.software.paymentservice.exception.OrderHasPaidException;
import com.software.paymentservice.service.OrderTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/orderTrans")
public class OrderTransController {
    @Autowired
    private OrderTransService orderTransService;


    /**
     * 调用支付服务
     * @param orderId
     * @param userId
     * @return
     */
    @RequestMapping("/makePayment")
    public ResultData makePayment(Integer orderId,Integer userId){
        ResultData resultData = new ResultData();
        resultData.setStatus(ResultData.Status.error);

        try{
            resultData.setBo(orderTransService.makePayment(orderId,userId));
            resultData.setStatus(ResultData.Status.success);
        }
        catch (OrderHasPaidException e){
            resultData.setMessage(e.getMessage());
        }
        catch (AccountBalanceInsufficientException e){
            resultData.setMessage(e.getMessage());
        }
        catch (Exception e){
            resultData.setMessage("订单交易服务调用异常");
        }

        return resultData;
    }
}
