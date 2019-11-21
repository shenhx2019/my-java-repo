package com.java8.designpatterninaction.creational.factory.entity;

/**
 * IAnimal
 */
public interface IAnimal {

    void speak();

    default void run(){
        System.out.print("我不会跑，但是我会飞");
    }

    default IAnimal newInstance(){
        throw new NullPointerException();
    }
    
}