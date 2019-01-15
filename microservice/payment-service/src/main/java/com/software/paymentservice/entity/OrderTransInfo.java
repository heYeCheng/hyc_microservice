package com.software.paymentservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 订单交易实体类定义
 */
@Table(name = "order_trans")
@Entity
public class OrderTransInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trans_id")
    private Integer transId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "trans_count")
    private BigInteger transCount;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigInteger getTransCount() {
        return transCount;
    }

    public void setTransCount(BigInteger transCount) {
        this.transCount = transCount;
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
