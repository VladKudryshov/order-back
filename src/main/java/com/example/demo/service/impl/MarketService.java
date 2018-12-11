package com.example.demo.service.impl;

import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.IMarketService;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService implements IMarketService{
    @Autowired
    private IMarketDAO marketDAO;

    @Autowired
    private IProductService productService;

    @Override
    public Market save(Market entity) {
        return marketDAO.save(entity);
    }

    @Override
    public Market edit(Market entity) {
        get(entity.getLanding());
        return null;
    }

    @Override
    public void remove(String landing) {
        Market market = get(landing);
        marketDAO.removeById(market.getId());
    }

    @Override
    public Market get(String landing) {
        return marketDAO.getByLanding(landing).orElseThrow(() ->
                new DataNotFoundException("Market not found",
                        TypeError.NOT_FOUND,
                        ObjectTypes.MARKET,
                        Operations.GET));
    }

    @Override
    public List<Market> getAll() {
        return marketDAO.getAll();
    }

    @Override
    public List<MarketProduct> getProducts(String landing) {
        Market market = get(landing);
        return productService.getProductsMarket(market.getId());
    }

    @Override
    public MarketProduct addProduct(String landing, MarketProduct product) {
        Market market = get(landing);
        return productService.addProductToMarket(market.getId(),product);
    }

    @Override
    public void deleteMarketProduct(String landing, String landingProduct) {
        Market market = get(landing);
        productService.deleteProductByMarket(market.getId(),landingProduct);
    }

    @Override
    public MarketProduct getProduct(String landing, String landingProduct) {
        Market market = get(landing);
        return productService.getProductByMarket(market.getId(),landingProduct);
    }
}
