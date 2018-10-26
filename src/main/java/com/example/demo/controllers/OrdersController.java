package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.Order;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "/api/orders", description = "Get information about orders")
@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 401, message = "Unauthorized")})
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class OrdersController extends IControllerApp<Order> {
    @Autowired
    IOrdersService service;

    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public Map<String, OrderStatus> getOrderIds() {
        return service.getOrdersIds();
    }

    @Override
    protected Order getEntity(Order entity) {
        return service.getEntity(entity);
    }

    @Override
    protected List<Order> getEntities() {
        return service.getAll();
    }

    @Override
    protected Boolean removeEntity(Order entity) {
        return service.removeEntity(entity);
    }

    @Override
    protected Order saveEntity(Order entity) {
        return service.saveEntity(entity);
    }

    @Override
    protected Order editEntity(Order entity) {
        return service.editEntity(entity);
    }
}
