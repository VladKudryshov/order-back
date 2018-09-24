package com.example.demo.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private String producer;
    private Dimension dimension;
    private Integer factor;
    private Double price;
    private String imgUrl;

    public void setDimension(String value) {
        this.dimension = Dimension.valueOf(value);
    }

}

