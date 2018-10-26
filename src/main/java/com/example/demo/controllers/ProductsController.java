package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.service.IProductsService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/api/product", description = "Get information about products")
@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 401, message = "Unauthorized")})
@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class ProductsController extends IControllerApp<Product> {

    @Autowired
    IProductsService service;

    @Override
    protected Product getEntity(Product entity) {
        return service.getEntity(entity);
    }

    @Override
    @Deprecated
    protected List<Product> getEntities() { return Lists.newArrayList(); }

    @Override
    protected Boolean removeEntity(Product entity) {
        return service.removeEntity(entity);
    }

    @Override
    protected Product saveEntity(Product entity) {
        return service.saveEntity(entity);
    }

    @Override
    protected Product editEntity(Product entity) {
        return service.editEntity(entity);
    }

    @PostMapping("getProducts")
    public List<Product> getProductsByMarket(@RequestBody Market market) {
        return service.getProductsByMarket(market);
    }
}
