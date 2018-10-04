package com.example.demo.dao.impl;

import com.example.demo.dao.IOrderDAO;
import com.example.demo.utils.JDBCUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAO implements IOrderDAO {

    public List<String> getAllOrders() {
        List<String> orders = new ArrayList<>();

        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT order_id" +
                    " FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(resultSet.getString("order_id"));
            }
        } catch (Exception e) {
        }

        return orders;
    }

    @Override
    public String getOrderInfo(String orderId) {
        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT array_to_json(array_agg(json_build_object(" +
                    "                                   'name', r.name," +
                    "                                   'weight', r.quantity :: DOUBLE PRECISION / r.factor," +
                    "                                   'dimension', r.dimension," +
                    "                                   'totalPrice', r.price * r.quantity :: DOUBLE PRECISION / r.factor" +
                    "                               ))) as products" +
                    " FROM (" +
                    "       SELECT" +
                    "         p.name," +
                    "         sum(b.quantity) quantity," +
                    "         max(plm.price) price," +
                    "         max(d.factor) factor," +
                    "         d.dimension" +
                    "       FROM booking b" +
                    "         INNER JOIN product p" +
                    "           ON b.product_id = p.id" +
                    "         INNER JOIN price_list_market plm" +
                    "           ON plm.product_id = p.id" +
                    "         INNER JOIN dimension d" +
                    "           ON d.id = plm.dimension_id" +
                    "       WHERE booking_id IN (SELECT unnest(booking_ids)" +
                    "                            FROM orders" +
                    "                            WHERE order_id = ?)" +
                    "       GROUP BY 1,5" +
                    "     ) AS r");
            preparedStatement.setString(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("products");

        } catch (Exception e) {
        }
        return Strings.EMPTY;
    }

    @Override
    public String getNames(String userId) {
        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ORDER);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Strings.EMPTY;
    }
}
