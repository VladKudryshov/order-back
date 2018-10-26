package com.example.demo.service.impl;

import com.example.demo.dao.IAddressDAO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Cart;
import com.example.demo.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService{
    @Autowired
    IAddressDAO addressDAO;

    @Override
    public Cart saveEntity(Cart entity) {
        Address address = entity.getAddress();
        entity.setAddress(addressDAO.save(address));
        return entity;
    }

    @Override
    public Cart editEntity(Cart entity) {
        return null;
    }

    @Override
    public boolean removeEntity(Cart entity) {
        return false;
    }

    @Override
    public Cart getEntity(Cart entity) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }
}
