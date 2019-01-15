package com.software.commodityservice.service.impl;

import com.software.commodityservice.entity.Commodity;
import com.software.commodityservice.repository.CommodityRepository;
import com.software.commodityservice.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityDao;

    /**
     * 根据商品id获取商品库存数量
     * @param commodityId
     * @return
     */
    @Override
    public Integer getCommodityCountById(Integer commodityId) {
        Commodity commodity = commodityDao.findByCommodityId(commodityId);

        if(commodity != null){
            return  commodity.getCommodityCount();
        }

        return null;
    }

    /**
     * 更加商品id更新商品库存数量
     * @param commodityId
     * @param amount
     */
    @Override
    @Transactional
    public void updateCommodityCountById(Integer commodityId, Integer amount) {
        commodityDao.updateCommodityCount(commodityId,amount);
    }

    /**
     * 根据商品id获取商品价格
     * @param commodityId
     * @return
     */
    @Override
    public BigInteger getCommodityPriceById(Integer commodityId) {
        Commodity commodity = commodityDao.findByCommodityId(commodityId);

        if(commodity != null){
            return commodity.getPrice();
        }

        return null;
    }
}
