package com.dto.request;

public class GetOrderDetailReq {
    private int productId;
    private int qty;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "GetOrderDetailReq{" +
                "productId=" + productId +
                ", qty=" + qty +
                '}';
    }
}
