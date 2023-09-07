package com.controller;

import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.LoanOfferResponse;
import com.dto.response.Product;
import com.service.IOrderService;
import com.util.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/get/all/products")
    public LoanOfferResponse getAllProducts() {
        List<Product> productList = orderService.getAllProducts();
        return LoanOfferResponse.generateResponse(
                productList,
                ApplicationConstant.SuccessStatusCode,
                ApplicationConstant.SuccessMsg);
    }

    @PostMapping("/get/order/single/request")
    public LoanOfferResponse getOrderSingleCalculation(@RequestBody GetOrderDetailReq getOrderDetailReq) {
        CommonResponse commonResponse = orderService.getOrderSingleCalculation(getOrderDetailReq);
        if (commonResponse.isRes()) {
            return LoanOfferResponse.generateResponse(
                    commonResponse.getValue(),
                    ApplicationConstant.SuccessStatusCode,
                    ApplicationConstant.SuccessMsg);
        } else {
            return LoanOfferResponse.generateResponse(
                    null,
                    commonResponse.getStatusCode(),
                    commonResponse.getMsg());
        }
    }

    @PostMapping("/create/order")
    public LoanOfferResponse placeOrder(@RequestBody CreateOrderReq createOrderReq) {
        CommonResponse commonResponse = orderService.placeOrder(createOrderReq);
        if (commonResponse.isRes()) {
            return LoanOfferResponse.generateResponse(
                    null,
                    ApplicationConstant.SuccessStatusCode,
                    ApplicationConstant.SuccessMsg);
        } else {
            return LoanOfferResponse.generateResponse(
                    null,
                    commonResponse.getStatusCode(),
                    commonResponse.getMsg());
        }
    }
}
