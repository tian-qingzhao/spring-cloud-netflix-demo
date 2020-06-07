package com.tian.springcloud.orderserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tian
 * @Date: 2020/2/24 17:56
 * @Desc:
 */
public class Order implements Serializable {

    private String orderNo;
    private Date createTime;
    private String productName;
    private double productPrice;
    private Long userId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
