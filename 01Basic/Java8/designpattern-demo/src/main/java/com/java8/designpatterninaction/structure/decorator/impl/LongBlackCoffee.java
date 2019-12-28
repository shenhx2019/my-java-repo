package com.java8.designpatterninaction.structure.decorator.impl;

import com.java8.designpatterninaction.structure.decorator.Coffee;

/**
 * @author shenhx
 */
public class LongBlackCoffee extends Coffee {

    public LongBlackCoffee() {
        setPrice(11f);
        setDescription("美国进口咖啡");
    }
}
