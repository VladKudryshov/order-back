package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.service.abstr.AppServices;

import java.util.Map;

public interface IOrdersService extends AppServices<Order> {
    Map<String, OrderStatus> getOrdersIds();
}
