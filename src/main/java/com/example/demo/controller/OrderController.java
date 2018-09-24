package com.example.demo.controller;

import com.example.demo.dao.IProductDAO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"http://localhost:4200"})
public class OrderController {

    @Autowired
    IProductDAO productDAO;

    @GetMapping("")
    public String getNames() {
        return productDAO.getNames();
    }

}
