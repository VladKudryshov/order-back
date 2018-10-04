package com.example.demo.dao;

import java.util.List;

public interface IOrderDAO {
    String SQL_GET_ORDER = "SELECT array_to_json(array_agg(objects)) AS data" +
            " FROM (" +
            "       SELECT" +
            "         json_build_object(" +
            "             'name',p.name," +
            "             'quantity',sum(b.quantity::DOUBLE PRECISION/d.factor)," +
            "             'dimension',d.dimension," +
            "             'price',plm.price," +
            "             'totalPrice',sum(plm.price/d.factor*b.quantity)" +
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
            "         INNER JOIN user_entity ue" +
            "          ON b.user_id = ue.id" +
            "       WHERE ue.id = ?" +
            "       GROUP BY p.name,plm.price,d.dimension" +
            "     ) AS t;";

    List<String> getAllOrders();

    String getOrderInfo(String orderId);

    String getNames(String userId);
}
