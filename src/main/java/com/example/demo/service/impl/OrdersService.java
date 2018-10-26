package com.example.demo.service.impl;

import com.example.demo.dao.IOrdersDAO;
import com.example.demo.entity.Order;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    IOrdersDAO dao;


    @Override
    public Map<String, OrderStatus> getOrdersIds() {
        return dao.getOrdersIds();
    }

    @Override
    public Order saveEntity(Order entity) {
        return dao.save(entity);
    }

    @Override
    public Order editEntity(Order entity) {
        return dao.update(entity);
    }

    @Override
    public boolean removeEntity(Order entity) {
        return dao.removeById(entity.getId());
    }

    @Override
    public Order getEntity(Order entity) {
        return dao.getEntityById(entity.getId());
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
