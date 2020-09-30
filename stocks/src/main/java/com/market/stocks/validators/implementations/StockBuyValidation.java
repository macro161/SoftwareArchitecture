package com.market.stocks.validators.implementations;

import com.market.stocks.model.Stock;
import com.market.stocks.model.User;
import com.market.stocks.validators.interfaces.IStockBuyValidator;
import org.springframework.stereotype.Component;

@Component
public class StockBuyValidation implements IStockBuyValidator {
    @Override
    public void executeBuyValidations(Stock stock, User user) throws Exception {
        if (user.getMoney() < stock.getPrice()) {
            throw new Exception("User do not have enough money to buy a stock");
        }

        if (stock.getAvailableAmount() < 1) {
            throw new Exception("All available amout of stock is sold out");
        }
    }
}
