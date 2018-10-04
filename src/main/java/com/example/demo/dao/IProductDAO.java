package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Map;

public interface IProductDAO {
    String SQL_GET_MARKET_PRODUCT = "SELECT p.*, plm.price,d.dimension,d.factor" +
            " FROM price_list_market plm" +
            "  INNER JOIN market m" +
            "    ON plm.market_id = m.id" +
            "  INNER JOIN product p" +
            "    ON plm.product_id = p.id" +
            "  INNER JOIN dimension d" +
            "    ON plm.dimension_id = d.id";

    String SQL_GET_MARKET_PRODUCT_BY_PRICE = "SELECT p.*, plm.price,d.dimension,d.factor" +
            " FROM price_list_market plm" +
            "  INNER JOIN market m" +
            "    ON plm.market_id = m.id" +
            "  INNER JOIN product p" +
            "    ON plm.product_id = p.id" +
            "  INNER JOIN dimension d" +
            "    ON plm.dimension_id = d.id" +
            "  WHERE plm.price < ?";

    String SQL_ADD_PRODUCT = "INSERT INTO booking (product_id,quantity) VALUES (?,?)";


    List<Product> getProductMarket();

    Boolean addProduct(Map<String,Object> params);


    List<Product> getProductMarket(Integer price);
}
