package com.software.commodityservice.repository;

import com.software.commodityservice.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品持久层接口定义
 */
public interface CommodityRepository extends JpaRepository<Commodity,Integer> {
    /**
     * 根据id查询商品信息
     * @return
     */
    Commodity findByCommodityId(Integer commodityId);

    /**
     * 根据商品id更新商品库存信息
     * @param commodityId
     * @param amount
     * @return
     */
    @Query(value = "update tbl_commodity_info set commodity_count = commodity_count + ?2 " +
            "where commodity_id = ?1",nativeQuery = true)
    @Modifying
    Integer updateCommodityCount(Integer commodityId, Integer amount);
}
