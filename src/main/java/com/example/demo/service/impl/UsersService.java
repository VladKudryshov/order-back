package com.example.demo.service.impl;

import com.example.demo.dao.IUsersDAO;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.UserOperations;
import com.example.demo.exceptions.InvalidParamsException;
import com.example.demo.service.IUsersService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IUsersService {
    @Autowired
    IUsersDAO dao;

    @Override
    public User saveEntity(User entity) {
        return dao.save(entity);
    }

    @Override
    public User editEntity(User entity) {
        return dao.update(entity);
    }

    public User editEntity(User entity, UserOperations operations) {
        checkModel(entity,operations);
        return dao.update(entity);
    }


    @Override
    public boolean removeEntity(User entity) {
        return dao.removeById(entity.getId());
    }

    @Override
    public User getEntity(User entity) {
        checkModel(entity,UserOperations.GET);
        return dao.getEntityById(entity.getId());
    }

    @Override
    public List<User> getAll() {
        return dao.getEntities();
    }

    @Override
    public void removeAll() {
        dao.removeAll();
    }

    private void checkModel(User entity, UserOperations operation){
        List<String> errorFields = entity.errorField(operation);
        if(CollectionUtils.isNotEmpty(errorFields)){
            throw new InvalidParamsException(errorFields, ObjectTypes.USER, Operations.EDIT);
        }
    }
}
