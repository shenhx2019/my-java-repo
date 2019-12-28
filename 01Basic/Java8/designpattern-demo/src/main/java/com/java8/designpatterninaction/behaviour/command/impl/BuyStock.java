package com.java8.designpatterninaction.behaviour.command.impl;


import com.java8.designpatterninaction.behaviour.command.Order;
import com.java8.designpatterninaction.behaviour.command.Stock;
import com.java8.designpatterninaction.behaviour.command.param.StockRequest;

public class BuyStock extends Order {

    Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy(stock.getQuantity());
    }
}
