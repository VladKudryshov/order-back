package com.example.demo.core.mappers;

import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketMapper {
    private ResultSet data;

    public MarketMapper entity(ResultSet result) {
        this.data = result;
        return this;
    }

    public Market convert() throws SQLException {
        Market market = new Market();
        market.setId(data.getInt("user_id"));
        market.setName(data.getString("name"));
        return market;
    }
}
