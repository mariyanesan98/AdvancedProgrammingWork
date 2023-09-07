package com.service.impl;

import com.dao.IOrderDAO;
import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.request.OrderInfoReq;
import com.dto.response.CommonResponse;
import com.dto.response.Product;
import com.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements IOrderService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    IOrderDAO orderDAO;
    @Override
    public List<Product> getAllProducts() {
        return orderDAO.getAllProducts();
    }

    @Override
    public CommonResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq) {
        return orderDAO.getOrderSingleCalculation(getOrderDetailReq);
    }

    @Override
    public CommonResponse placeOrder(CreateOrderReq createOrderReq) {
        logger.info("placeOrder------------------->" + createOrderReq.toString());
        String orderRequest = "";
        for (OrderInfoReq req : createOrderReq.getOrderDetail()) {
            orderRequest = orderRequest + req.getProductId() +"||"+req.getQty()+",";
        }
        orderRequest = orderRequest.substring(0, orderRequest.length()-1);
        logger.info("placeOrder-orderRequest------------------>" + orderRequest);
        return orderDAO.placeOrder(createOrderReq,orderRequest);
    }
}
