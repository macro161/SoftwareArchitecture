package com.market.stocks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Stock {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(float availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column

    private String stockName;

    @Column
    private float price;

    @Column
    private float availableAmount;

    @Column
    private String description;
}
