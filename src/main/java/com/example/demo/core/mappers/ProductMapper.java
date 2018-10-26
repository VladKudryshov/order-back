package com.example.demo.core.mappers;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {
    private ResultSet data;

    public ProductMapper entity(ResultSet result) {
        this.data = result;
        return this;
    }

    public Product convert() throws SQLException {
        Product product = new Product();
        product.setId(data.getInt("product_id"));
        product.setName(data.getString("product"));
        return product;
    }
}
