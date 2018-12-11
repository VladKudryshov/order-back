package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.Market;

import java.util.Optional;

public interface IMarketDAO extends BaseDao<Market,String>{
    Optional<Market> getByLanding(String landing);
}
