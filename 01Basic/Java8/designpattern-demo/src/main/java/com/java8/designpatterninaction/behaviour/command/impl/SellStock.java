package com.java8.designpatterninaction.behaviour.command.impl;


import com.java8.designpatterninaction.behaviour.command.Order;
import com.java8.designpatterninaction.behaviour.command.Stock;
import com.java8.designpatterninaction.behaviour.command.param.StockRequest;

public class SellStock extends Order {

    Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell(stock.getQuantity());
    }
}
