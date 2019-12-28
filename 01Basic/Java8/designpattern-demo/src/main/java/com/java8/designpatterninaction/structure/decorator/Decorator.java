package com.java8.designpatterninaction.structure.decorator;

/**
 * @author shenhx
 */
public abstract class Decorator extends Drink {

     private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " --- " + super.getDescription() + ", 价格：" + this.getPrice();
    }

    @Override
    public double getCost() {
        return drink.getCost() + this.getPrice();
    }
}
