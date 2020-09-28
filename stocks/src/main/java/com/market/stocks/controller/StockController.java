package com.market.stocks.controller;

import com.market.stocks.model.Stock;
import com.market.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StockController {

    private StockRepository stockRepository;

    @Autowired
    public StockController(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/stockslisting")
    public String getAllStocksListing(Model model) {
        model.addAttribute("stockslisting", stockRepository.findAll());
        return "stockslisting";
    }

    @GetMapping(path = "/stockslisting/add")
    public String createStock(Model model) {
        model.addAttribute("stock",new Stock());
        return "edit";
    }

    @PostMapping(path = "/stockslisting/save")
    public String saveCustomer(Stock stock) {
        stockRepository.save(stock);
        return "redirect:/stockslisting";
    }

    @GetMapping(path = "/stockslisting/edit/{id}")
    public String editCustomer(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("stock", stockRepository.findById(id));
        return "edit";
    }

    @GetMapping(path = "/stockslisting/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") long id) {
        stockRepository.deleteById(id);
        return "redirect:/stockslisting";
    }

}
