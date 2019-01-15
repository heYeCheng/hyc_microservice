package com.software.paymentservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.remote.order-service}",path = "/rest/order")
public interface OrderFeignService {

    @RequestMapping(value = "/getOrderInfoByOrderId",method = RequestMethod.GET)
    ResultData getOrderInfoByOrderId(@RequestParam(value = "orderId") Integer orderId);

    @RequestMapping(value = "/updateOrderStatus",method = RequestMethod.GET)
    ResultData updateOrderStatus(@RequestParam(value = "orderId") Integer orderId, @RequestParam(value = "payStatus") Integer payStatus);
}
