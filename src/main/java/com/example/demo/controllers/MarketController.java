package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.service.IMarketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "/api/markets", description = "Get information about markets")
@RestController
@RequestMapping("api/markets")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class MarketController extends IControllerApp<Market, String> {

    @Autowired
    private IMarketService service;

    @RequestMapping(value = "/{landing}", method = RequestMethod.GET)
    protected Market get(@PathVariable String landing) {
        return service.get(landing);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    protected List<Market> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{landing}", method = RequestMethod.DELETE)
    protected void remove(@PathVariable String landing) {
        service.remove(landing);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    protected Market save(@RequestBody Market entity) {
        return service.save(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    protected Market edit(@RequestBody Market entity) {
        return null;
    }

    @RequestMapping(value = "/{landing}/products", method = RequestMethod.GET)
    protected List<MarketProduct> getProductsMarket(@PathVariable String landing) {
        return service.getProducts(landing);
    }

    @RequestMapping(value = "/{landing}/products", method = RequestMethod.POST)
    protected MarketProduct addProductToMarket(@PathVariable String landing,
                                         @RequestBody MarketProduct product) {
        return service.addProduct(landing, product);
    }

    @RequestMapping(value = "/{landing}/{landingProduct}", method = RequestMethod.GET)
    protected Product getProductByMarket(@PathVariable String landing,
                                         @PathVariable String landingProduct) {
        return service.getProduct(landing, landingProduct);
    }

    @RequestMapping(value = "/{landing}/{landingProduct}", method = RequestMethod.DELETE)
    protected void deleteProductByMarket(@PathVariable String landing,
                                         @PathVariable String landingProduct) {
        service.deleteMarketProduct(landing, landingProduct);
    }


}
