package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

/**
 * @author shenhx
 */
public class Cucumber implements Vegetables {
    @Override
    public String getName() {
        return "青瓜";
    }

    @Override
    public Double getPrice() {
        return 1.2;
    }

    @Override
    public String getColor() {
        return "绿色";
    }
}
