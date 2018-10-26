package com.example.demo.service.impl;

import com.example.demo.dao.IProductDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService implements IProductsService{
    @Autowired
    IProductDAO dao;

    @Override
    public List<Product> getProductsByMarket(Market market) {
        return dao.getProductFromMarket(market);
    }

    @Override
    public Product saveEntity(Product entity) {
        return dao.save(entity);
    }

    @Override
    public Product editEntity(Product entity) {
        return dao.update(entity);
    }

    @Override
    public boolean removeEntity(Product entity) {
        return dao.removeById(entity.getId());
    }

    @Override
    public Product getEntity(Product entity) {
        return dao.getEntityById(entity.getId());
    }

    @Override
    @Deprecated
    public List<Product> getAll() {
        return null;
    }
}
