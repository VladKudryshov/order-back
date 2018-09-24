package com.example.demo.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
@Table(name = "price_list_market")
@Data
public class PriceListMarket {
    private Product product;
    private Market market;
    private Dimension dimension;
    private Double price;
    private String address;
}
