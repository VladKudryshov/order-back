package com.example.demo.dao.impl;

import com.example.demo.core.constatns.ErrorConstants;
import com.example.demo.core.mappers.AddressMapper;
import com.example.demo.core.mappers.ProductMapper;
import com.example.demo.core.mappers.UserMapper;
import com.example.demo.core.utils.JDBCUtils;
import com.example.demo.dao.IOrdersDAO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.exceptions.DataNotFoundException;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@Repository
public class OrdersDAO implements IOrdersDAO {

    private static final String SQL_GET_ORDER_IDS = "SELECT id,status FROM orders";

    @Override
    public Order save(Order entity) {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean removeById(String entityId) {
        return false;
    }

    @Override
    public Order getEntityById(String entityId) {

        try (Connection connection = JDBCUtils.getAccountsConnection();
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement("SELECT o.*, ue.login,ue.email,ue.password,ue.user_role,ue.user_status, a.city, a.street, a.house, a.flat" +
                            " FROM orders o" +
                            "  INNER JOIN user_entity ue ON o.user_id = ue.user_id" +
                            "  INNER JOIN address a ON o.address_id = a.address_id" +
                            " WHERE o.id = ?")){
            preparedStatement.setString(1,entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Order order = new Order();
            order.setId(resultSet.getString("id"));
            order.setAddress(new AddressMapper().entity(resultSet).convert());
            order.setUser(new UserMapper().entity(resultSet).convert());

            PreparedStatement ps = Objects
                    .requireNonNull(connection)
                    .prepareStatement("SELECT p.product_id, p.name product" +
                            " FROM booking b" +
                            "  INNER JOIN product p ON b.product_id = p.product_id" +
                            " WHERE b.id = ?");

            ps.setString(1,resultSet.getString("booking_id"));

            ResultSet productsResult = ps.executeQuery();
            List<Product> products = Lists.newArrayList();
            while (productsResult.next()){
                products.add(new ProductMapper().entity(productsResult).convert());
            }
            order.setProducts(products);
            return order;
        } catch (Exception e) {
           throw new DataNotFoundException(ErrorConstants.NOT_FOUND,ObjectTypes.ORDER, Operations.GET);
        }
    }

    @Override
    public List<Order> getEntities() {
        return null;
    }

    @Override
    public Map<String, OrderStatus> getOrdersIds() {
        Map<String, OrderStatus> idsByStatus = new HashMap<>();
        try (Connection connection = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_GET_ORDER_IDS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                idsByStatus.put(id, status);
            }
        } catch (Exception ignored) {
        }
        return idsByStatus;
    }
}
