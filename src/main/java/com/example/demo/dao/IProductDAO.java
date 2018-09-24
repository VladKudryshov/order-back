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
    String SQL_GET_ORDER = "SELECT array_to_json(array_agg(objects)) AS data" +
            " FROM (" +
            "       SELECT" +
            "         json_build_object(" +
            "             'name',p.name," +
            "             'quantity',to_number(to_char(sum(b.quantity::DOUBLE PRECISION/d.factor), 'FM9999999999990D999'), 'FM9999999999990D999')," +
            "             'dimension',d.dimension," +
            "             'price',to_number(to_char(plm.price::real, 'FM9990D99'), 'FM9990D99')," +
            "             'totalPrice',to_number(to_char(sum(plm.price/d.factor*b.quantity), 'FM9999990D99'),'FM9999990D99')" +
            "         ) as objects" +
            "       FROM price_list_market plm" +
            "         INNER JOIN market m" +
            "           ON plm.market_id = m.id" +
            "         INNER JOIN product p" +
            "           ON plm.product_id = p.id" +
            "         INNER JOIN dimension d" +
            "           ON plm.dimension_id = d.id" +
            "         INNER JOIN booking b" +
            "           ON p.id = b.product_id" +
            "       GROUP BY p.name,plm.price,d.dimension" +
            "     ) AS t;";

    List<Product> getProductMarket();

    Boolean addProduct(Map<String,Object> params);

    String getNames();

    List<Product> getProductMarket(Integer price);
}
