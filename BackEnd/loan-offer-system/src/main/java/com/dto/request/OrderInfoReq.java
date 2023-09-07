package com.dto.request;

public class OrderInfoReq {
    private int productId;
    private double qty;

    public OrderInfoReq() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderInfoReq{" +
                "productId=" + productId +
                ", qty=" + qty +
                '}';
    }
}
