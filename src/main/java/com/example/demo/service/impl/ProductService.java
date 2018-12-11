package com.example.demo.service.impl;

import com.example.demo.dao.IProductDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public Product edit(Product entity) {
        return null;
    }

    @Override
    public void remove(Integer field) {

    }

    @Override
    public Product get(Integer field) {
        return null;
    }

    public Product getByLanding(String landingProduct) {
        return productDAO.getByLandingProduct(landingProduct)
                .orElseThrow(() -> new DataNotFoundException("Product not found",
                        TypeError.NOT_FOUND,
                        ObjectTypes.PRODUCT,
                        Operations.GET));
    }

    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<MarketProduct> getProductsMarket(Integer id) {
        return productDAO.getAllByMarket(id);
    }

    @Override
    public MarketProduct addProductToMarket(Integer id, MarketProduct product) {
        return productDAO.addProductToMarket(id, product);
    }

    @Override
    public void deleteProductByMarket(Integer id, String landingProduct) {
        MarketProduct productByMarket = getProductByMarket(id, landingProduct);
        productDAO.deleteMarketProduct(productByMarket.getInternalId());
    }

    @Override
    public MarketProduct getProductByMarket(Integer id, String landingProduct) {
        Product product = getByLanding(landingProduct);
        return productDAO.getMarketProduct(product.getId(),id)
                .orElseThrow(() -> new DataNotFoundException("Product not found",
                        TypeError.NOT_FOUND,
                        ObjectTypes.PRODUCT,
                        Operations.GET));
    }
}
