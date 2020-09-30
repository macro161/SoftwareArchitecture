package com.market.stocks.validators.implementations;

import com.market.stocks.model.Stock;
import com.market.stocks.validators.interfaces.IStockValidator;
import org.springframework.stereotype.Component;

@Component
public class StocksStockValidator implements IStockValidator {

    StockValueValidator stockValueValidator = new StockValueValidator();
    StockEmptyValueValidator stockEmptyValueValidator = new StockEmptyValueValidator();

    @Override
    public void executeValidations(Stock stock) throws Exception {
        stockValueValidator.validateInput(stock);
        stockEmptyValueValidator.validateInput(stock);
    }
}
