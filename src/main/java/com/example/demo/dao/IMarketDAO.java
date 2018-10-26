package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.Market;
import com.example.demo.entity.Order;
import com.example.demo.entity.enums.OrderStatus;

import java.util.Map;

public interface IMarketDAO extends BaseDao<Market, Integer> {

}
