package com.market.stocks.validators.implementations;

import com.market.stocks.model.Stock;
import com.market.stocks.validators.interfaces.IStockInputValidator;

public class StockValueValidator implements IStockInputValidator {

    private static int MAX_STOCK_NAME_LENGTH = 4;

    @Override
    public void validateInput(Stock stock) throws Exception {
        if (stock.getStockName().length() > MAX_STOCK_NAME_LENGTH) {
            throw new Exception("Stock name cannot have name longer then 4 characters");
        }
        if (stock.getPrice() < 0) {
            throw new Exception("Stock price cannot be lower then zero");
        }
        if (stock.getAvailableAmount() < 0) {
            throw new Exception("Available stock amount cannot be lower then zero");
        }
        if (stock.getDescription().length() > 255) {
            throw new Exception("Stock description cannot be longer then 255 characters");
        }
    }
}
