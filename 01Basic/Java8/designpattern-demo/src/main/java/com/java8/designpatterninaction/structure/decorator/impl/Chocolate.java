package com.java8.designpatterninaction.structure.decorator.impl;

import com.java8.designpatterninaction.structure.decorator.Decorator;
import com.java8.designpatterninaction.structure.decorator.Drink;

public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setPrice(1.5f);
        setDescription("加巧克力");
    }
}
