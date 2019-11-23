package com.java8.designpatterninaction.creational.factory.entity;

import com.java8.designpatterninaction.creational.factory.entity.abstractfactory.Meat;

/**
 * Chicken
 */
public class Chicken implements IAnimal, Meat {
    @Override
    public void speak() {
        System.out.print("gegege");
    }

    @Override
    public IAnimal newInstance(){
        return new Chicken();
    }

    @Override
    public String getName() {
        return "普通鸡";
    }

    @Override
    public Double getPrice() {
        return 12.3;
    }
}