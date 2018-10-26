package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.enums.OrderStatus;

import java.util.Map;

public interface IOrdersDAO extends BaseDao<Order, String> {

    Map<String, OrderStatus> getOrdersIds();
}
