package com.market.stocks.validators.interfaces;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;

public interface IStockBuyValidator {
    void executeBuyValidations(Stock stock, User user) throws Exception;
}
