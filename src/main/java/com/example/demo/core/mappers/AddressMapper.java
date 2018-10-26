package com.example.demo.core.mappers;

import com.example.demo.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper {
    private ResultSet data;

    public AddressMapper entity(ResultSet result) {
        this.data = result;
        return this;
    }

    public Address convert() throws SQLException {
        Address address = new Address();
        address.setId(data.getInt("address_id"));
        address.setCity(data.getString("city"));
        address.setStreet(data.getString("street"));
        address.setHouse(data.getString("house"));
        address.setFlat(data.getString("flat"));
        return address;
    }
}
