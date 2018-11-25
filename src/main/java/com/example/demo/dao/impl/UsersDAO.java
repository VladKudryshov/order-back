package com.example.demo.dao.impl;

import com.example.demo.core.mappers.UserMapper;
import com.example.demo.core.utils.DataBaseUtils;
import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.dao.IUsersDAO;
import com.example.demo.entity.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UsersDAO implements IUsersDAO {
    private final static Logger LOGGER = ProjectUtils.getLogger(UsersDAO.class);
    private static final String SQL_GET_USERS = "SELECT * FROM user_entity ue INNER JOIN user_info u ON ue.user_id = u.user_id";
    private static final String SQL_DELETE_ALL = "DELETE FROM user_entity";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User save(User entity) {
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue(UserMapper.EMAIL, entity.getEmail())
                    .addValue(UserMapper.PASSWORD, entity.getPassword());

            ResultSet resultSet = namedParameterJdbcTemplate.execute(SQL_CREATE_USER,
                    parameters,
                    PreparedStatement::executeQuery);
            entity.setId(resultSet.getInt(UserMapper.USER_ID));
        } catch (DataAccessException | SQLException e) {
            LOGGER.error("Can't create user. {}", ProjectUtils.getLocationException(e));
            throw new RuntimeException(e.getMessage());
        }

        return entity;
    }

    @Override
    public User update(User entity) {
        try (Connection connection = DataBaseUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_USER_UPDATE_INFO);

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setInt(2, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserMapper().convertFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.error("Can't update user. {}", ProjectUtils.getLocationException(e));
        }
        return entity;
    }

    @Override
    public void removeById(Integer userId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(UserMapper.USER_ID, userId, Types.INTEGER);
        try {
            namedParameterJdbcTemplate.execute(SQL_REMOVE_USER_BY_ID,
                    params,
                    PreparedStatement::execute);
        } catch (DataAccessException e) {
            LOGGER.error("Can't delete user: ", e);
        }
    }

    @Override
    public Optional<User> getById(Integer userId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(UserMapper.USER_ID, userId, Types.INTEGER);

        try {
            return namedParameterJdbcTemplate
                    .query(SQL_GET_USER_BY_ID,
                            params,
                            new UserMapper())
                    .stream()
                    .findFirst();

        } catch (Exception e) {
            LOGGER.error("Can't get user: ", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        try {
            return namedParameterJdbcTemplate
                    .getJdbcOperations()
                    .query(SQL_GET_USERS, new UserMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Can't get users: ", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void removeAll() {
        try {
            namedParameterJdbcTemplate.execute(SQL_DELETE_ALL,
                    PreparedStatement::execute);
        } catch (DataAccessException e) {
            LOGGER.error("Can't delete users: ", e);
        }

    }

    @Override
    public void updateEmail(User entity) {
        try (Connection connection = DataBaseUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_USER_UPDATE_EMAIL);

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            LOGGER.error("Can't update user email. {}", ProjectUtils.getLocationException(e));
        }
    }

    @Override
    public void changePassword(User entity) {
        try (Connection connection = DataBaseUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_USER_UPDATE_PASSWORD);

            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            LOGGER.error("Can't update user password. {}", ProjectUtils.getLocationException(e));
        }
    }

    @Override
    public void changeRole(User entity) {
        try (Connection connection = DataBaseUtils.getAccountsConnection()) {
            PreparedStatement preparedStatement = Objects
                    .requireNonNull(connection)
                    .prepareStatement(SQL_USER_UPDATE_ROLE);

            preparedStatement.setString(1, entity.getRole().name());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            LOGGER.error("Can't update user role. {}", ProjectUtils.getLocationException(e));
        }

    }
}
