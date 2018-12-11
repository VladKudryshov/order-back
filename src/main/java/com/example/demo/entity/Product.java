package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
    @JsonIgnore
    private Integer id;
    private String name;
    private String landingProduct;

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

    public String getLandingProduct() {
        return landingProduct;
    }

    public void setLandingProduct(String landingProduct) {
        this.landingProduct = landingProduct;
    }
}
