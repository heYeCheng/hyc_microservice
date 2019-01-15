package com.software.accountservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


@Table(name = "tbl_user_acc")
@Entity
public class UserAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "acc_id")
    private Integer accId;

    @Column(name = "user_id",nullable = false)
    private Integer userId;

    @Column(name = "acc_balance")
    private BigInteger accBalance;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigInteger getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(BigInteger accBalance) {
        this.accBalance = accBalance;
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
