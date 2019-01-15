package com.software.orderservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.remote.commodity-service}",path = "/rest")
public interface CommodityFeignService {

    /**
     * 根据商品id获取商品库存数量
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/commodity/getCommodityCountById",method = RequestMethod.GET)
    ResultData getCommodityCountById(@RequestParam(value = "commodityId") Integer commodityId);

    /**
     * 根据商品id更新商品库存
     * @param commodityId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/commodity/updateStockByCommodityId",method = RequestMethod.GET)
    ResultData updateStockByCommodityId(@RequestParam("commodityId") Integer commodityId,
                                        @RequestParam("amount") Integer amount);
}
