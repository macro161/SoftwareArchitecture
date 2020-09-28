package com.market.stocks.controller;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.repository.IStockRepository;
import com.market.stocks.repository.IUserRepository;
import com.market.stocks.validators.MainValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class StockController {

    private IStockRepository stockRepository;
    private MainValidator stocksValidator;
    private IUserRepository userRepository;

    @Autowired
    public StockController(MainValidator stocksValidator, IStockRepository stockRepository, IUserRepository userRepository) {
        super();
        this.stocksValidator = stocksValidator;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/stockslisting")
    public String getAllStocksListing(Model model) {
        model.addAttribute("stockslisting", stockRepository.findAll());
        model.addAttribute("user", userRepository.findAll());
        return "stockslisting";
    }

    @GetMapping(path = "/stockslisting/add")
    public String createStock(Model model) {
        model.addAttribute("stock",new Stock());
        return "edit";
    }

    @PostMapping(path = "/stockslisting/save")
    public String saveCustomer(Stock stock) throws Exception {
        stocksValidator.executeValidations(stock);
        stockRepository.save(stock);
        return "redirect:/stockslisting";
    }

    @GetMapping(path = "/stockslisting/buy/{id}")
    public String buyStocks(@PathVariable(value = "id") long id) throws Exception {
        User user = userRepository.getOne(1L);
        Stock stock = stockRepository.getOne(id);
        user.setMoney(user.getMoney() - stock.getPrice());
        userRepository.save(user);
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