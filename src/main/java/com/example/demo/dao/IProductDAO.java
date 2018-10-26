package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface IProductDAO extends BaseDao<Product, Integer> {
    List<Product> getProductFromMarket(Market market);
}
