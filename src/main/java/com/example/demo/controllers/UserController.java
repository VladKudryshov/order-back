package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.UserOperations;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.IUsersService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "/api/user", description = "Get information about users")
@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 401, message = "Unauthorized")})
@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class UserController extends IControllerApp<User> {
    @Autowired
    IUsersService service;

    @Override
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    protected User getEntity(User entity) {
        return service.getEntity(entity);
    }

    @Override
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    protected List<User> getEntities() {
        return service.getAll();
    }

    @Override
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    protected Boolean removeEntity(User entity) {
        return service.removeEntity(entity);
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    protected User saveEntity(User entity) {
        return service.saveEntity(entity);
    }

    @Override
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    protected User editEntity(User entity) {
        return service.editEntity(entity);
    }

    @RequestMapping(value = "/changeLogin", method = RequestMethod.POST)
    protected User changeLogin(User entity) {
        return service.editEntity(entity, UserOperations.CHANGE_LOGIN);
    }

    @RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
    protected User changeEmail(User entity) {
        return service.editEntity(entity, UserOperations.CHANGE_EMAIL);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    protected User changePassword(User entity) {
        return service.editEntity(entity, UserOperations.CHANGE_PASSWORD);
    }

    @RequestMapping(value = "/removeAll", method = RequestMethod.GET)
    public void removeAll() {
        service.removeAll();
    }
}
