package com.example.demo.core.mappers;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.entity.enums.UserStatus;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class UserMapper implements RowMapper<User>{

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE = "user_role";
    public static final String USER_STATUS = "user_status";

    public User convertFromResultSet(ResultSet data) throws SQLException {
        User user = new User();
        user.setId(data.getInt("user_id"));
        user.setLogin(data.getString("name"));
        user.setEmail(data.getString("email"));
        user.setPassword(data.getString("password"));
        user.setRole(Role.valueOf(data.getString("user_role").toUpperCase()));
        user.setStatus(UserStatus.valueOf(data.getString("user_status").toUpperCase()));
        return user;
    }

    @Override
    public User mapRow(ResultSet data, int i) throws SQLException {
        User user = new User();
        user.setId(data.getInt(USER_ID));
        user.setLogin(data.getString(USER_NAME));
        user.setEmail(data.getString(EMAIL));
        user.setPassword(data.getString(PASSWORD));
        user.setRole(Role.valueOf(data.getString(USER_ROLE).toUpperCase()));
        user.setStatus(UserStatus.valueOf(data.getString(USER_STATUS).toUpperCase()));
        return null;
    }
}
