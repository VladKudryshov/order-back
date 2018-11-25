package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.User;

import java.util.Optional;

public interface IUsersDAO extends BaseDao<User, Integer> {
    String SQL_GET_USER_BY_ID = "SELECT * FROM user_entity ue INNER JOIN user_info ui " +
            " ON ue.user_id = ui.user_id WHERE ue.user_id = :user_id";

    String SQL_REMOVE_USER_BY_ID = "DELETE FROM user_entity WHERE user_id = :user_id RETURNING user_entity.user_id";
    String SQL_USER_UPDATE_PASSWORD = "UPDATE user_entity" +
            " SET" +
            "  password = coalesce(?,password)" +
            " WHERE user_id = ?" +
            " RETURNING user_entity.*";
    String SQL_USER_UPDATE_EMAIL = "UPDATE user_entity" +
            " SET" +
            "  email = coalesce(?,email)" +
            " WHERE user_id = ?" +
            " RETURNING user_entity.*";
    String SQL_USER_UPDATE_ROLE = "UPDATE user_entity" +
            " SET" +
            "  user_role = coalesce(?,user_role)" +
            " WHERE user_id = ?" +
            " RETURNING user_entity.*";
    String SQL_USER_UPDATE_INFO = "UPDATE user_entity" +
            " SET" +
            "  name = coalesce(?,name)" +
            " WHERE user_id = ?" +
            " RETURNING user_entity.*";
    String SQL_CREATE_USER = "SELECT * FROM create_user(:email, :password)";

    void removeAll();

    void updateEmail(User entity);

    void changePassword(User entity);

    void changeRole(User entity);

}
