package com.market.stocks.validators.interfaces;

import com.market.stocks.model.Stock;

public interface IStockValidator {
    void executeValidations(Stock stock) throws Exception;
}
