package com.java8.designpatterninaction.creational.factory.entity;

/**
 * 菜市场西
 */
public class WestFastFoodStore implements IAnimalFactory {

    @Override
    public Dog getDog() {
        return new BaiQieDog();
    }

    @Override
    public Chicken getChicken() {
        return new QingyuanChicken();
    }

    
}