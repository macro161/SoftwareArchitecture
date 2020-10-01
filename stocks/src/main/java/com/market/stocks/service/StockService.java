package com.market.stocks.service;

import com.market.stocks.dto.UserStockDTO;
import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.repository.IStockRepository;
import com.market.stocks.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockService {

    private IStockRepository stockRepository;
    private IUserRepository userRepository;

    @Autowired
    public StockService(IStockRepository stockRepository, IUserRepository userRepository) {
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    public List<Stock> getAllStocksListing() {
        return stockRepository.findAll();
    }

    public Stock createStock(Stock stock) {
        stockRepository.save(stock);
        return stock;
    }

    public UserStockDTO buyStocks(UserStockDTO userStockDTO) {
        return userStockDTO;
    }

    public Stock editStock(Stock stock) {
        return stock;
    }

    public Stock deleteStock(Stock stock) {
        return stock;
    }

}
