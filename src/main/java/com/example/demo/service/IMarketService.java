package com.example.demo.service;

import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.service.abstr.AppServices;

import java.util.List;

public interface IMarketService extends AppServices<Market,String>{
    List<MarketProduct> getProducts(String landing);

    MarketProduct addProduct(String landing, MarketProduct product);

    MarketProduct getProduct(String landing, String product);

    void deleteMarketProduct(String landing, String landingProduct);
}
