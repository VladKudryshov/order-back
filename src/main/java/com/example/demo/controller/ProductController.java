package com.example.demo.controller;

import com.example.demo.dao.IProductDAO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class ProductController {


    @Autowired
    IProductDAO productDAO;

    @GetMapping("")
    public List<Product> getProducts() {
        return productDAO.getProductMarket();
    }

    @PostMapping("")
    public List<Product> getProductsByPrice(@RequestBody Map<String,Object> map) {
        return productDAO.getProductMarket((Integer)map.get("price"));
    }

    @PostMapping("/add")
    public Boolean addProductToCard(@RequestBody Map<String,Object> params) {
        return productDAO.addProduct(params);
    }

}
