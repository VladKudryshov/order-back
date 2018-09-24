package com.example.demo.dao.impl;

import com.example.demo.dao.IProductDAO;
import com.example.demo.model.Product;
import com.example.demo.utils.JDBCUtils;
import com.example.demo.utils.MapperUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO implements IProductDAO {

    public List<Product> getProductMarket() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_MARKET_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(MapperUtils.productMapper(resultSet));
            }
        } catch (Exception e) {
        }

        return products;
    }


    public Boolean addProduct(Map<String, Object> params) {

        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_PRODUCT);
            preparedStatement.setInt(1, (int) params.get("product_id"));
            preparedStatement.setInt(2, (int) params.get("quantity"));
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<Product> getProductMarket(Integer price) {
        List<Product> products = new ArrayList<>();

        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_MARKET_PRODUCT_BY_PRICE);
            preparedStatement.setInt(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(MapperUtils.productMapper(resultSet));
            }
        } catch (Exception e) {
        }

        return products;
    }

    @Override
    public String getNames() {
        try (Connection conn = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ORDER);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Strings.EMPTY;
    }
}
