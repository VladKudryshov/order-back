package com.example.demo.core.mappers;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class UserMapper {
    private ResultSet data;

    public UserMapper entity(ResultSet result) {
        this.data = result;
        return this;
    }

    public User convert() throws SQLException {
        User user = new User();
        user.setId(data.getInt("user_id"));
        user.setLogin(data.getString("login"));
        user.setEmail(data.getString("email"));
        user.setPassword(data.getString("password"));
        user.setRole(Role.valueOf(data.getString("user_role").toUpperCase()));
        user.setStatus(UserStatus.valueOf(data.getString("user_status").toUpperCase()));
        return user;
    }
}
