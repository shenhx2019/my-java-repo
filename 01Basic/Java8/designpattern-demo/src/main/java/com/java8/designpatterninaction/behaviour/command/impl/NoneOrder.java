package com.java8.designpatterninaction.behaviour.command.impl;

import com.java8.designpatterninaction.behaviour.command.Order;
import com.java8.designpatterninaction.behaviour.command.param.StockRequest;

/**
 * @author shenhx
 */
public class NoneOrder extends Order {

    public NoneOrder(String messge) {
        this.setMessage(messge);
    }

    @Override
    public void execute() {

    }
}
