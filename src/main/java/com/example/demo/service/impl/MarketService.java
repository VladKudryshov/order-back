package com.example.demo.service.impl;

import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import com.example.demo.service.IMarketService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService implements IMarketService {
    @Autowired
    IMarketDAO dao;

    @Override
    public Market saveEntity(Market entity) {
        return dao.save(entity);
    }

    @Override
    public Market editEntity(Market entity) {
        return dao.update(entity);
    }

    @Override
    public boolean removeEntity(Market entity) {
        return dao.removeById(entity.getId());
    }

    @Override
    public Market getEntity(Market entity) {
        return dao.getEntityById(entity.getId());
    }

    @Override
    public List<Market> getAll() {
        return dao.getEntities();
    }
}
