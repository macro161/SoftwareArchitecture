package com.market.stocks.controller;

import com.market.stocks.dto.UserStockDTO;
import com.market.stocks.model.Stock;
import com.market.stocks.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        super();
        this.stockService = stockService;
    }


    @RequestMapping(value = "/stocklisting", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Stock> getAllStocksListing() {
        return stockService.getAllStocksListing();
    }

    @RequestMapping(value = "/createstock", method = RequestMethod.POST, headers = "Accept=application/json")
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }


    @RequestMapping(value = "/buystock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public UserStockDTO buyStocks(@RequestBody UserStockDTO userStockDTO) throws Exception {
        return stockService.buyStocks(userStockDTO);
    }

    @RequestMapping(value = "/editstock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Stock editCustomer(Stock stock) {
        return stockService.editStock(stock);
    }

    @RequestMapping(value = "/deletestock", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public Stock deleteStock(Stock stock) {
        return stockService.deleteStock(stock);
    }
}
