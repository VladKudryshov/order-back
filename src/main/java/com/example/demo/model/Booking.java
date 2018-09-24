package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Integer bookingId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
