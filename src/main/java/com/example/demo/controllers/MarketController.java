package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.Market;
import com.example.demo.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class MarketController extends IControllerApp<Market> {

    @Autowired
    IMarketService service;


    @Override
    protected Market getEntity(Market entity) {
        return null;
    }

    @Override
    protected List<Market> getEntities() {
        return null;
    }

    @Override
    protected Boolean removeEntity(Market entity) {
        return null;
    }

    @Override
    protected Market saveEntity(Market entity) {
        return service.saveEntity(entity);
    }

    @Override
    protected Market editEntity(Market entity) {
        return null;
    }
}
