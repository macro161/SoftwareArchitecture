package com.market.stocks.validators;

import com.market.stocks.model.Stock;
import org.springframework.util.StringUtils;

public class StockEmptyValueValidator implements IStocksValidator {
    @Override
    public void validate(Stock stock) throws Exception {
        if (StringUtils.isEmpty(stock.getStockName())) {
            throw new Exception("Stock name cannot be empty");
        }
        if (StringUtils.isEmpty(stock.getDescription())) {
            throw new Exception("Stock description cannot be empty");
        }
    }
}
