package com.java8.designpatterninaction.creational.factory.entity;

/**
 * ChickenFactory
 */
public class ChickenFactory {

    public Chicken getChicken(String name){
        if(name == "qy"){
            return new QingyuanChicken();
        }else if(name == "zj"){
            return new ZhanJiangChicken();
        }
        return null;
    }
}