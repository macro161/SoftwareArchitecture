package com.market.stocks.controller;

import com.market.stocks.model.Greeting;
import com.market.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestGreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private StockRepository stockRepository;

    public RestGreetingController(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @CrossOrigin
    @GetMapping("/restgreeting")
    public Sto greeting() {
        return name;
        );
    }
}
