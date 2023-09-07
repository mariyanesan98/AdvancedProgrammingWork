package com.dao;

public class OfferDAOConstant {
    public interface UserConstant{
        String INSERT_UPDATE_ADMIN_CUSTOMER = "{call insert_update_admin_customer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        String GET_CUSTOMER_DETAIL = "{call get_customer_detail(?)}";

        String GET_CUSTOMER_LIST = "{call get_customer_list()}";

        String LOGIN_USER = "{call user_login(?,?,?,?,?,?,?,?,?)}";
    }

    public interface OrderConstant{


        String GET_ALL_PRODUCTS = "{call get_all_products_proc()}";
        String GET_ORDER_DETAIL_REQUEST_PROC = "{call get_order_detail_request_proc(?,?,?,?,?,?,?,?,?,?,?)}";

        String CREATE_NEW_ORDER = "{call create_order_request(?,?,?,?,?,?)}";
    }
}
