package com.example.demo.entity;

import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.Role;
import com.example.demo.entity.enums.UserOperations;
import com.example.demo.entity.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private Role role;
    private UserStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<String> errorField(UserOperations operation){

        List<String> errorFields = Lists.newArrayList();
        if (Objects.isNull(id)){
            errorFields.add("user_id");
        }
        if (Objects.isNull(login) && operation.equals(UserOperations.CHANGE_LOGIN)){
            errorFields.add("login");
        }
        if (Objects.isNull(email) && operation.equals(UserOperations.CHANGE_EMAIL)){
            errorFields.add("email");
        }
        if (Objects.isNull(password) && operation.equals(UserOperations.CHANGE_PASSWORD)){
            errorFields.add("password");
        }

        return errorFields;
    }
}
