package com.software.paymentservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.remote.commodity-service}",path = "/rest")
public interface CommodityFeignService {

    /**
     * 根据商品id获取商品价格
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/commodity/getCommodityPriceById",method = RequestMethod.GET)
    ResultData getCommodityPriceById(@RequestParam(value = "commodityId") Integer commodityId);
}
