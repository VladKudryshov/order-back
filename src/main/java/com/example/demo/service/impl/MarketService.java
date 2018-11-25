package com.example.demo.service.impl;

import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService implements IMarketService{

    @Autowired
    private IMarketDAO dao;

    @Override
    public Market save(Market entity) {
        return dao.save(entity);
    }

    @Override
    public Market edit(Market entity) {
        get(entity.getId());
        return null;
    }

    @Override
    public void remove(Integer id) {
        get(id);
        dao.removeById(id);
    }

    @Override
    public Market get(Integer id) {
        return dao.getById(id).orElseThrow(() ->
                new DataNotFoundException("Market not found",
                        TypeError.NOT_FOUND,
                        ObjectTypes.MARKET,
                        Operations.GET));
    }

    @Override
    public List<Market> getAll() {
        return dao.getAll();
    }
}
