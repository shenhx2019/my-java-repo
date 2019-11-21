package com.java8.designpatterninaction.creational.factory.entity;

/**
 * Dog
 */
public class Dog implements IAnimal {
    @Override
    public void speak() {
        System.out.print("wowowowo");
    }

    @Override
    public IAnimal newInstance(){
        return new Dog();
    }
}