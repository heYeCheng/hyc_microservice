package com.software.commodityservice.service;


import java.math.BigInteger;

/**
 * 商品服务层接口定义
 */
public interface CommodityService {
    /**
     * 根据商品id获取商品库存数量
     * @param commodityId
     * @return
     */
    Integer getCommodityCountById(Integer commodityId);


    /**
     * 根据商品id更新商品库存数量
     * @param commodityId
     * @param amount
     */
    void updateCommodityCountById(Integer commodityId,Integer amount);

    /**
     * 根据商品id获取商品价格
     * @param commodityId
     * @return
     */
    BigInteger getCommodityPriceById(Integer commodityId);
}
