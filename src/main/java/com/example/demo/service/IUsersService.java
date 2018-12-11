package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.UserOperations;
import com.example.demo.service.abstr.AppServices;

import java.util.List;

public interface IUsersService extends AppServices<User, Integer>{

    void removeAll();

    void changePersonalData(User entity);

    void changeEmail(User entity);

    void changePassword(User entity);

    User changeRole(User entity);
}
