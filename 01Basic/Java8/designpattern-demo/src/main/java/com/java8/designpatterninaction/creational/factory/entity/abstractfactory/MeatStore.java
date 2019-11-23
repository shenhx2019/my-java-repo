package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

import com.java8.designpatterninaction.creational.factory.entity.Chicken;

/**
 * 肉档
 * @author shenhx
 */
public class MeatStore extends FastFoodStoreAbstractFactory {

    @Override
    public Meat getMeat(String name) throws NoYourNeedException {
        switch (name){
            case "鸡肉":{
                return  new Chicken();
            }
            default:{
                throw  new NoYourNeedException("没有您需要的购买的东西");
            }
        }
    }

    @Override
    public Vegetables getVegetables(String name) throws NotAllowSaleCompletingProductsException {
        throw new NotAllowSaleCompletingProductsException("要买蔬菜，请到隔壁去");
    }
}