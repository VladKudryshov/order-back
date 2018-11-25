package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.IMarketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "/api/markets", description = "Get information about markets")
@RestController
@RequestMapping("api/markets")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class MarketController extends IControllerApp<Market> {

    @Autowired
    private IMarketService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected Market get(@PathVariable Integer id) {
        return service.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    protected List<Market> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected void remove(@PathVariable Integer id) {
        service.remove(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    protected Market save(@RequestBody Market entity) {
        return service.save(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    protected Market edit(@RequestBody Market entity) {
        return null;
    }
}
