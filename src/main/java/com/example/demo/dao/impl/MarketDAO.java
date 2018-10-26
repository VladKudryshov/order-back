package com.example.demo.dao.impl;

import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarketDAO implements IMarketDAO {
    @Override
    public Market save(Market entity) {
        return null;
    }

    @Override
    public Market update(Market entity) {
        return null;
    }

    @Override
    public boolean removeById(Integer entityId) {
        return false;
    }

    @Override
    public Market getEntityById(Integer entityId) {
        return null;
    }

    @Override
    public List<Market> getEntities() {
        return null;
    }
}
