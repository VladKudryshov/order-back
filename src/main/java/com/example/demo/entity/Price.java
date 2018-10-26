package com.example.demo.entity;

import com.example.demo.entity.enums.Dimension;

public class Price {
    private Integer price;
    private Dimension dimension;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
