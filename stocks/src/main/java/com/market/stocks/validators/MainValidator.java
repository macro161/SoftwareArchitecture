package com.market.stocks.validators;

import com.market.stocks.model.Stock;

public interface MainValidator {
    void executeValidations(Stock stock) throws Exception;
}
