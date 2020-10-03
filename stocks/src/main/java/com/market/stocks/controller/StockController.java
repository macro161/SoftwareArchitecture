package com.market.stocks.controller;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.service.StockService;
import com.market.stocks.service.UserService;
import com.market.stocks.validators.implementations.StockBuyValidation;
import com.market.stocks.validators.implementations.StocksStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class StockController {

    private StockService stockService;
    private UserService userService;

    private StocksStockValidator validator;
    private StockBuyValidation stockBuyValidation;

    @Autowired
    public StockController(StockService stockService, StocksStockValidator stocksStockValidator, StockBuyValidation stockBuyValidation, UserService userService) {
        super();
        this.stockService = stockService;
        this.validator = stocksStockValidator;
        this.stockBuyValidation = stockBuyValidation;
        this.userService = userService;
    }

    @RequestMapping(value = "/stocklisting", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Stock> getAllStocksListing() {

        return stockService.getAllStocksListing();
    }

    @RequestMapping(value = "/createstock", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity createStock(@RequestBody Stock stock) throws Exception {
        try {
            validator.executeValidations(stock);
            stockService.createStock(stock);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/buystock/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity buyStocks(@PathVariable Long id) throws Exception {
        try {
            stockBuyValidation.executeBuyValidations(stockService.getStockById(id), userService.getUser());
            stockService.buyStocks(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/editstock", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity editStock(@RequestBody Stock stock) throws Exception {
        try {
            validator.executeValidations(stock);
            stockService.editStock(stock);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/deletestock/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseBody
    public Stock deleteStock(@PathVariable Long id) {
        return stockService.deleteStock(id);
    }

    @GetMapping(value = "/user", headers = "Accept=application/json")
    public User getUser() {
        return userService.getUser();
    }
}
