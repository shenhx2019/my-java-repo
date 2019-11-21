package com.java8.designpatterninaction.creational.factory.entity;

/**
 * 抽象工厂
 */
public interface IAnimalFactory {

    Dog getDog();

    Chicken getChicken();
}