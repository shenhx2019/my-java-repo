package com.java8.designpatterninaction.structure.decorator.impl;

import com.java8.designpatterninaction.structure.decorator.Decorator;
import com.java8.designpatterninaction.structure.decorator.Drink;

public class Suger extends Decorator {

    public Suger(Drink drink) {
        super(drink);
        setPrice(0.5f);
        setDescription("加糖");
    }
}
