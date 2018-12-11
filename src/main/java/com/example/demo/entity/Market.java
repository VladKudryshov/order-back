package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Market {
    @JsonIgnore
    private Integer id;
    private String name;
    private String description;
    private String landing;
    private List<String> phones;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
