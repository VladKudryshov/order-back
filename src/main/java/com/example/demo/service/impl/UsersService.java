package com.example.demo.service.impl;

import com.example.demo.core.constatns.ErrorConstants;
import com.example.demo.dao.IUsersDAO;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.DuplicateEntityException;
import com.example.demo.service.IUsersService;
import com.example.demo.core.utils.ProjectUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsersService implements IUsersService {
    private final static Logger LOGGER = ProjectUtils.getLogger(UsersService.class);
    private final static ObjectTypes OBJECT_TYPE = ObjectTypes.USER;

    @Autowired
    IUsersDAO dao;

    @Override
    public User save(User entity) {
        return dao.save(entity);
    }

    @Override
    public User edit(User entity) {
        get(entity.getId());
        return dao.update(entity);
    }

    @Override
    public void remove(Integer id) {
        get(id);
        dao.removeById(id);
    }

    @Override
    public User get(Integer id) {
        return dao.getById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorConstants.NOT_FOUND,
                        TypeError.NOT_FOUND,
                        OBJECT_TYPE,
                        Operations.GET));
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void removeAll() {
        dao.removeAll();
    }

    @Override
    public void changePersonalData(User entity) {
        User user = get(entity.getId());
        user.setLogin(entity.getLogin());
    }

    @Override
    public void changeEmail(User entity) {
        User user = get(entity.getId());
        user.setEmail(entity.getEmail());
        dao.updateEmail(user);
    }

    @Override
    public void changePassword(User entity) {
        User user = get(entity.getId());
        user.setPassword(entity.getPassword());
        dao.changePassword(entity);
    }

    @Override
    public User changeRole(User entity) {
        User user = get(entity.getId());
        user.setRole(entity.getRole());
        dao.changeRole(user);
        return user;
    }

}
