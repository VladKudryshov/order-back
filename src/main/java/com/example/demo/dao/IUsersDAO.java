package com.example.demo.dao;

import com.example.demo.dao.abstr.BaseDao;
import com.example.demo.entity.User;

public interface IUsersDAO extends BaseDao<User,Integer> {
    void removeAll();
}
