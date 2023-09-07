package com.dao.impl;

import com.dao.IOrderDAO;
import com.dao.OfferDAOConstant;
import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class OrderDAOImpl implements IOrderDAO {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Product> getAllProducts() {
        Long startTime = System.currentTimeMillis();
        List<Product> list = null;
        Connection connection = null;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.OrderConstant.GET_ALL_PRODUCTS);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            if (resultSet != null) {
                list = new ArrayList<>();
                while (resultSet.next()) {
                    Product response = new Product();
                    response.setProductId(resultSet.getInt(1));
                    response.setProductName(resultSet.getString(2));
                    list.add(response);
                }
            }
        } catch (SQLException exception) {
            logger.info("An error occured in getAllProducts " + exception.toString());
        } finally {
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            logger.info("Time taken for getAllProducts in seconds: " + (double) (System.currentTimeMillis() - startTime) / 1000);
        }
        return list;
    }

    @Override
    public CommonResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq) {
        Long startTime = System.currentTimeMillis();
        CommonResponse response = new CommonResponse();
        Connection connection = null;
        CallableStatement callableStatement;
        try {
            logger.info("getOrderSingleCalculation-request------------------>" + getOrderDetailReq.toString());

            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.OrderConstant.GET_ORDER_DETAIL_REQUEST_PROC);
            callableStatement.setObject(1, getOrderDetailReq.getProductId(), Types.INTEGER);
            callableStatement.setObject(2, getOrderDetailReq.getQty(), Types.INTEGER);

            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.DOUBLE);

            callableStatement.registerOutParameter(9,Types.BOOLEAN);
            callableStatement.registerOutParameter(10,Types.INTEGER);
            callableStatement.registerOutParameter(11,Types.VARCHAR);

            callableStatement.execute();
            callableStatement.getResultSet();
            response.setRes((Boolean) callableStatement.getObject(9));
            response.setStatusCode((Integer) callableStatement.getObject(10));
            response.setMsg((String) callableStatement.getObject(11));


            if(response.isRes()){
                GetOrderDetailRes getOrderDetailRes = new GetOrderDetailRes();
                getOrderDetailRes.setProductId(callableStatement.getInt(3));
                getOrderDetailRes.setCategoryName(callableStatement.getString(4));
                getOrderDetailRes.setBrandName(callableStatement.getString(5));
                getOrderDetailRes.setQty(callableStatement.getInt(6));
                getOrderDetailRes.setProductName(callableStatement.getString(7));
                getOrderDetailRes.setSubTotal(callableStatement.getDouble(8));

                response.setValue(getOrderDetailRes);
            }
        } catch (SQLException exception) {
            logger.info("An error occured in getOrderSingleCalculation " + exception.toString());
        } finally {
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            logger.info("Time taken for getOrderSingleCalculation in seconds: " + (double) (System.currentTimeMillis() - startTime) / 1000);
        }
        return response;
    }

    @Override
    public CommonResponse placeOrder(CreateOrderReq createOrderReq, String orderRequest) {
        Long startTime = System.currentTimeMillis();
        CommonResponse response = new CommonResponse();
        Connection connection = null;
        CallableStatement callableStatement;
        try {
            logger.info("placeOrder-request------------------>" + createOrderReq.toString());
            logger.info("placeOrder-request------------------>" + orderRequest);

            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.OrderConstant.CREATE_NEW_ORDER);
            callableStatement.setObject(1, createOrderReq.getPaymentOption(), Types.INTEGER);
            callableStatement.setObject(2, createOrderReq.getUserId(), Types.INTEGER);
            callableStatement.setObject(3, orderRequest, Types.VARCHAR);


            callableStatement.registerOutParameter(4,Types.BOOLEAN);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.VARCHAR);

            callableStatement.execute();
            callableStatement.getResultSet();
            response.setRes((Boolean) callableStatement.getObject(4));
            response.setStatusCode((Integer) callableStatement.getObject(5));
            response.setMsg((String) callableStatement.getObject(6));

        } catch (SQLException exception) {
            logger.info("An error occured in placeOrder " + exception.toString());
        } finally {
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            logger.info("Time taken for placeOrder in seconds: " + (double) (System.currentTimeMillis() - startTime) / 1000);
        }
        return response;
    }
}
