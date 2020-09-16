package com.market.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksApplication {

	@Autowired
	private static StockRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StocksApplication.class, args);
		repository.save( new Stock())
	}

	@Autowired
	public void setRepository(StockRepository repository) {
		StocksApplication.repository = repository;
	}
}
