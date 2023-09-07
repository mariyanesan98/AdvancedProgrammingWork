package com.dao;

import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.Product;

import java.util.List;

public interface IOrderDAO {
    /**
     * getAllProducts
     * @return
     */
    public List<Product> getAllProducts();

    /**
     * getOrderSingleCalculation
     * @param getOrderDetailReq
     * @return
     */
    CommonResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq);

    /**
     * placeOrder
     * @param createOrderReq
     * @param orderRequest
     * @return
     */
    CommonResponse placeOrder(CreateOrderReq createOrderReq, String orderRequest);
}
