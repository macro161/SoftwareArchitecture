package com.market.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private StockRepository stockRepository;

    @Autowired
    public DataLoader(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stock s1 = new Stock();
        s1.setStockName("TSLA");
        s1.setPrice(420.69f);
        stockRepository.save(s1);

    }
}
