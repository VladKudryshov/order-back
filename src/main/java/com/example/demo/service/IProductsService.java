package com.example.demo.service;

import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.service.abstr.AppServices;

import java.util.List;

public interface IProductsService extends AppServices<Product>{
    List<Product> getProductsByMarket(Market market);
}
