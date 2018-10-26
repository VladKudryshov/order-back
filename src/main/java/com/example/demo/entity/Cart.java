package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private User user;
    private Address address;
    private List<Product> products;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
