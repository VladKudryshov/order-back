package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO extends BaseDao<Product,String>{
    List<MarketProduct> getAllByMarket(Integer id);

    MarketProduct addProductToMarket(Integer id, MarketProduct product);

    Optional<Product> getByLandingProduct(String landingProduct);

    Optional<MarketProduct> getMarketProduct(Integer productId, Integer marketId);

    void deleteMarketProduct(Integer internalId);
}
