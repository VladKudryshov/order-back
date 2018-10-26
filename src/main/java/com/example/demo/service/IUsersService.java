package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.UserOperations;
import com.example.demo.service.abstr.AppServices;

public interface IUsersService extends AppServices<User> {
    void removeAll();
    User editEntity(User entity, UserOperations operation);

}
