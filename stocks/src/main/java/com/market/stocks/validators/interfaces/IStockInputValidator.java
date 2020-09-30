package com.market.stocks.validators.interfaces;

import com.market.stocks.model.Stock;

public interface IStockInputValidator {
    void validateInput(Stock stock) throws Exception;
}
