package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.Cart;
import com.example.demo.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class CartController extends IControllerApp<Cart> {

    @Autowired
    IBookingService service;

    @Override
    protected Cart getEntity(Cart entity) {
        return null;
    }

    @Override
    protected List<Cart> getEntities() {
        return null;
    }

    @Override
    protected Boolean removeEntity(Cart entity) {
        return null;
    }

    @Override
    protected Cart saveEntity(Cart entity) {
        return service.saveEntity(entity);
    }

    @Override
    protected Cart editEntity(Cart entity) {
        return null;
    }
}
