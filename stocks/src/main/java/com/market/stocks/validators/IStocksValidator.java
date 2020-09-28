package com.market.stocks.validators;

import com.market.stocks.model.Stock;

public interface IStocksValidator {
    void validate(Stock stock) throws Exception;
}
