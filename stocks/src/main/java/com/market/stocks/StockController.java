package com.market.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

    private StockRepository stockRepository;

    @Autowired
    public StockController(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @GetMapping(path = "/stockslisting")
    public String getAllStocksListing(Model model) {
        model.addAttribute("stockslisting", stockRepository.findAll());
        return "stockslisting";
    }

}
