package com.java8.designpatterninaction.structure.decorator;

/**
 * 定义普通咖啡
 * @author shenhx
 */
public class Coffee extends Drink {

    public Coffee() {
        setPrice(3f);
        setDescription("斋啡");
    }

    @Override
    public String getDescription() {
        return super.getDescription()  + ", 价格：" + this.getPrice();
    }

    @Override
    public double getCost() {
        return this.getPrice();
    }
}
