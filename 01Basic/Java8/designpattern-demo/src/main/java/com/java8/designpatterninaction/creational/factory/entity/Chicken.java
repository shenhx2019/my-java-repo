package com.java8.designpatterninaction.creational.factory.entity;

/**
 * Chicken
 */
public class Chicken implements IAnimal {
    @Override
    public void speak() {
        System.out.print("gegege");
    }

    @Override
    public IAnimal newInstance(){
        return new Chicken();
    }
}