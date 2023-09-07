package com.dto.request;

import java.util.List;

public class CreateOrderReq {
    private int paymentOption;
    private int  userId ;
    private List<OrderInfoReq> orderDetail;

    public CreateOrderReq() {
    }

    public int getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(int paymentOption) {
        this.paymentOption = paymentOption;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderInfoReq> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderInfoReq> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "CreateOrderReq{" +
                "paymentOption=" + paymentOption +
                ", userId=" + userId +
                ", orderDetail=" + orderDetail.toString() +
                '}';
    }
}
