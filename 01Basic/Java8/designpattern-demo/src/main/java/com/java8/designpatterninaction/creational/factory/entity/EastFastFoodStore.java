package com.java8.designpatterninaction.creational.factory.entity;

/**
 * 菜市场东
 */
public class EastFastFoodStore implements IAnimalFactory {

    @Override
    public Dog getDog() {
        return new ShaoKaoDog();
    }

    @Override
    public Chicken getChicken() {
        return new ZhanJiangChicken();
    }

    
}