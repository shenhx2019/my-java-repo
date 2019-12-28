package com.java8.designpatterninaction.structure.decorator.impl;

import com.java8.designpatterninaction.structure.decorator.Coffee;

/**
 * @author shenhx
 */
public class ShortBlackCoffee extends Coffee {

    public ShortBlackCoffee() {
        setPrice(6f);
        setDescription("国内咖啡");
    }
}
