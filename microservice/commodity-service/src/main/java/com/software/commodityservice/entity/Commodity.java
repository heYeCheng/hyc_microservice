package com.software.commodityservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 商品实体类
 */
@Entity
@Table(name = "tbl_commodity_info")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commodity_id")
    private Integer commodityId;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "commodity_count")
    private Integer commodityCount;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Integer getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
