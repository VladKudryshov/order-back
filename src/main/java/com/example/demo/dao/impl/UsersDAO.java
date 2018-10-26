package com.example.demo.dao.impl;

import com.example.demo.core.constatns.ErrorConstants;
import com.example.demo.core.mappers.UserMapper;
import com.example.demo.dao.IUsersDAO;
import com.example.demo.entity.User;
import com.example.demo.core.utils.JDBCUtils;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.InvalidParamsException;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class UsersDAO implements IUsersDAO {

    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user_entity WHERE user_id = ?";
    private static final String SQL_REMOVE_USER_BY_ID = "DELETE FROM user_entity WHERE user_id = ? RETURNING user_entity.user_id";
    private static final String SQL_USER_UPDATE = "UPDATE user_entity" +
            " SET" +
            "  login = coalesce(?,login)," +
            "  email = coalesce(?,email)," +
            "  password = coalesce(?,password)" +
            " WHERE user_id = ?" +
            " RETURNING user_entity.*";
    private static final String SQL_CREATE_USER = "INSERT INTO user_entity (login, email, password) VALUES (?,?,?) RETURNING user_entity.*";

    @Override
    public User save(User entity) {
        try (Connection connection = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_CREATE_USER);

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new UserMapper().entity(resultSet).convert();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public User update(User entity) {
        try (Connection connection = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_USER_UPDATE);

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserMapper().entity(resultSet).convert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException(ErrorConstants.NOT_FOUND, ObjectTypes.USER, Operations.EDIT);
    }

    @Override
    public boolean removeById(Integer userId) {
        boolean deleted = Boolean.FALSE;
        try (Connection connection = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_REMOVE_USER_BY_ID);

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            deleted = resultSet.next();
        } catch (Exception ignored) {
        }
        if (!deleted) {
            throw new DataNotFoundException(ErrorConstants.CANT_DELETE, ObjectTypes.USER, Operations.REMOVE);
        }

        return deleted;
    }

    @Override
    public User getEntityById(Integer userId) {

        User user = null;
        try (Connection connection = JDBCUtils.getAccountsConnection();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection)
                     .prepareStatement(SQL_GET_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new UserMapper().entity(resultSet).convert();
            }
        } catch (Exception ignored) {
        }
        if (Objects.isNull(user)) {
            throw new DataNotFoundException(ErrorConstants.NOT_FOUND, ObjectTypes.USER, Operations.GET);
        }
        return user;
    }

    @Override
    public List<User> getEntities() {
        List<User> users = Lists.newArrayList();
        try (Connection connection = JDBCUtils.getAccountsConnection();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection)
                     .prepareStatement("SELECT * FROM user_entity")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new UserMapper().entity(resultSet).convert());
            }
        } catch (Exception ignored) {
        }
        if (CollectionUtils.isEmpty(users)) {
            throw new DataNotFoundException(ErrorConstants.NOT_FOUND, ObjectTypes.USER, Operations.GET);
        }
        return users;
    }

    @Override
    public void removeAll() {
        boolean deleted = Boolean.FALSE;
        try (Connection connection = JDBCUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement("DELETE FROM user_entity");

            ResultSet resultSet = preparedStatement.executeQuery();
            deleted = resultSet.next();
        } catch (Exception ignored) {}
        if (!deleted) {
            throw new DataNotFoundException(ErrorConstants.CANT_DELETE, ObjectTypes.USER, Operations.REMOVE);
        }
    }
}
