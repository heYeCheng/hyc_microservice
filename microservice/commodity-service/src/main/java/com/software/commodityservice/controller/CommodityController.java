package com.software.commodityservice.controller;

import com.software.commodityservice.service.CommodityService;
import com.software.commonservice.common.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "/getCommodityCountById")
    public ResultData getCommodityCountById(Integer commodityId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(commodityService.getCommodityCountById(commodityId));
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用商品服务层异常！");
        }

        return resultData;
    }

    @GetMapping(value = "/updateStockByCommodityId")
    public ResultData updateStockByCommodityId(@RequestParam(value = "commodityId",required = true) Integer commodityId,
                                               @RequestParam(value = "amount",required = true)Integer amount){
        ResultData resultData = new ResultData();

        try {
            commodityService.updateCommodityCountById(commodityId,amount);
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用商品服务层异常！");
        }

        return resultData;
    }

    @GetMapping(value = "/getCommodityPriceById")
    public ResultData getCommodityPriceById(@RequestParam(value = "commodityId",required = true) Integer commodityId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(commodityService.getCommodityPriceById(commodityId));
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用商品服务层异常！");
        }

        return resultData;
    }
}
