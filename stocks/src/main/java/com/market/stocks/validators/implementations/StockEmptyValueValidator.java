package com.market.stocks.validators.implementations;

import com.market.stocks.model.Stock;
import com.market.stocks.validators.interfaces.IStockInputValidator;
import org.springframework.util.StringUtils;

public class StockEmptyValueValidator implements IStockInputValidator {
    @Override
    public void validateInput(Stock stock) throws Exception {
        if (StringUtils.isEmpty(stock.getStockName())) {
            throw new Exception("Stock name cannot be empty");
        }
        if (StringUtils.isEmpty(stock.getDescription())) {
            throw new Exception("Stock description cannot be empty");
        }
    }
}
