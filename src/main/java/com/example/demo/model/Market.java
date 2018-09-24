package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "market")
@Data
public class Market {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String phone;
    private String address;
}
