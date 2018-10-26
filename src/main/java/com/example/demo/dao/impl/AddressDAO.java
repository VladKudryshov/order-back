package com.example.demo.dao.impl;

import com.example.demo.core.mappers.AddressMapper;
import com.example.demo.core.mappers.UserMapper;
import com.example.demo.core.utils.JDBCUtils;
import com.example.demo.dao.IAddressDAO;
import com.example.demo.entity.Address;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

@Repository
public class AddressDAO implements IAddressDAO {
    @Override
    public Address save(Address entity) {
        try(Connection connection = JDBCUtils.getAccountsConnection()){
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement("INSERT INTO address (city, street, house, flat) VALUES (?,?,?,?) RETURNING address.*");

            preparedStatement.setString(1,entity.getCity());
            preparedStatement.setString(2,entity.getStreet());
            preparedStatement.setString(3,entity.getHouse());
            preparedStatement.setString(4,entity.getFlat());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new AddressMapper().entity(resultSet).convert();
        }catch (Exception ignored){}
        return entity;
    }

    @Override
    public Address update(Address entity) {
        return null;
    }

    @Override
    public boolean removeById(Integer entityId) {
        return false;
    }

    @Override
    public Address getEntityById(Integer entityId) {
        return null;
    }

    @Override
    public List<Address> getEntities() {
        return null;
    }
}
