package com.market.stocks.service;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.repository.IStockRepository;
import com.market.stocks.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
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

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).get();
    }

    public Stock buyStocks(Long id) {
        Stock stock = stockRepository.findById(id).get();
        stock.setAvailableAmount(stock.getAvailableAmount() - 1);
        stockRepository.save(stock);
        User user = userRepository.findById(1L).get();
        user.setMoney(user.getMoney() - stock.getPrice());
        userRepository.save(user);
        return stock;
    }

    public Stock editStock(Stock stock) {
        stockRepository.save(stock);
        return stock;
    }

    public Stock deleteStock(Long id) {
        Stock stock = stockRepository.findById(id).get();
        stockRepository.delete(stock);

        return stock;
    }

}
