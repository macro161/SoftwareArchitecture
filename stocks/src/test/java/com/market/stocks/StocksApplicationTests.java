package com.market.stocks;

import com.market.stocks.controller.MainController;
import com.market.stocks.controller.StockController;
import com.market.stocks.model.Stock;
import com.market.stocks.repository.IStockRepository;
import com.market.stocks.validators.implementations.StocksStockValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

@SpringBootTest
class StocksApplicationTests {

	@Autowired
	StockController controller;
	@Autowired
	StocksStockValidator validator;
	@Autowired
	IStockRepository stockRepository;
	@Autowired
	MainController mainController;


	@Test
	void contextLoads() throws Exception {
		Stock stock = new Stock();
		stock.setStockName("TESA");
		stock.setAvailableAmount(1.1f);
		stock.setDescription("DESK");
		stock.setPrice(1.1f);
		assertThat(stock.getStockName()).isEqualTo("TESA");
		assertThat(controller.getAllStocksListing().get(0).getStockName()).isEqualTo(stockRepository.findAll().get(0).getStockName());
		assertThat(mainController.index()).isEqualTo("index");
		assertThat(controller.createStock(stock)).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
		stock.setStockName("12345");
		assertThat(controller.createStock(stock).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		controller.deleteStock(1l);
		controller.getUser();
		assertThat(controller.buyStocks(10l).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(controller.buyStocks(2l).getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(controller.editStock(stock).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(controller.editStock(controller.getAllStocksListing().get(0)).getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
