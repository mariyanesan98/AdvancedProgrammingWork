package com.service;

import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.Product;

import java.util.List;

public interface IOrderService {
    /**
     * getAllProducts
     * @return
     */
    List<Product> getAllProducts();

    /**
     * getOrderSingleCalculation
     * @param getOrderDetailReq
     * @return
     */
    CommonResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq);

    /**
     * placeOrder
     * @param createOrderReq
     * @return
     */
    CommonResponse placeOrder(CreateOrderReq createOrderReq);
}
