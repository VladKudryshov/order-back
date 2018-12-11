package com.example.demo.service;

import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.service.abstr.AppServices;

import java.util.List;

public interface IProductService extends AppServices<Product, Integer> {

    List<MarketProduct> getProductsMarket(Integer id);

    MarketProduct addProductToMarket(Integer id, MarketProduct product);

    MarketProduct getProductByMarket(Integer id, String product);

    void deleteProductByMarket(Integer id, String landingProduct);
}
