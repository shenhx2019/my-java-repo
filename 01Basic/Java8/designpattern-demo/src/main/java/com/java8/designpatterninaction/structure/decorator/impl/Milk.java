package com.java8.designpatterninaction.structure.decorator.impl;

import com.java8.designpatterninaction.structure.decorator.Decorator;
import com.java8.designpatterninaction.structure.decorator.Drink;

public class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        setPrice(1f);
        setDescription("加奶");
    }
}
