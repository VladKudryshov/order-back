package com.example.demo.core.mappers;

import com.example.demo.entity.Address;
import com.example.demo.entity.Price;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.Dimension;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceMapper {
    private ResultSet data;

    public PriceMapper entity(ResultSet result) {
        this.data = result;
        return this;
    }

    public Price convert() throws SQLException {
        Price price = new Price();
        price.setPrice(data.getInt("price"));
        price.setDimension(Dimension.valueOf(data.getString("dimension")));
        return price;
    }
}
