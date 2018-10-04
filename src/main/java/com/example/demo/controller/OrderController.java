package com.example.demo.controller;

import com.example.demo.dao.IOrderDAO;
import com.example.demo.dao.IProductDAO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"https://http://products-order.herokuapp.com"})
public class OrderController {

    @Autowired
    IOrderDAO orderDAO;

    @GetMapping("")
    public List<String> getOrders() {
        return orderDAO.getAllOrders();
    }

    @PostMapping("")
    public String getOrder(@RequestBody Map<String,Object> params) {
        return orderDAO.getOrderInfo((String)params.get("orderId"));
    }

    @PostMapping("/current")
    public String getNames(@RequestBody Map<String,Object> params) {
        return orderDAO.getNames((String)params.get("userId"));
    }
}
