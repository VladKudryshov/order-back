package com.example.demo.utils;

import com.example.demo.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperUtils {

    public static Product productMapper(ResultSet resultSet) throws SQLException{
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setProducer(resultSet.getString("producer"));
        product.setDimension(resultSet.getString("dimension"));
        product.setPrice(resultSet.getDouble("price"));
        product.setFactor(resultSet.getInt("factor"));
        product.setImgUrl(resultSet.getString("img"));
        return product;
    }
}
