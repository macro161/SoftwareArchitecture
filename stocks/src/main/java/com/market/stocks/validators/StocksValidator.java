package com.market.stocks.validators;

import com.market.stocks.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StocksValidator implements MainValidator {

    StockValueValidator stockValueValidator = new StockValueValidator();
    StockEmptyValueValidator stockEmptyValueValidator = new StockEmptyValueValidator();

    @Override
    public void executeValidations(Stock stock) throws Exception {
        stockValueValidator.validate(stock);
        stockEmptyValueValidator.validate(stock);
    }
}
