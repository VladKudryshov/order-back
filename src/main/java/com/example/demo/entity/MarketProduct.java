package com.example.demo.entity;

import com.example.demo.entity.enums.Dimension;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MarketProduct extends Product {
    @JsonIgnore
    private Integer internalId;
    private String description;
    private String producer;
    private Dimension dimension;
    private Double price;

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String productInfoPgType() {
        return String.format("(%s,%s,%s,%.2f)",
                this.description, this.producer, this.dimension.name(), this.price);
    }
}
