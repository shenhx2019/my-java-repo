package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

/**
 * 菜市场东
 */
public class VegetablesStore extends FastFoodStoreAbstractFactory {


    @Override
    public Meat getMeat(String name) throws NotAllowSaleCompletingProductsException {
        throw new NotAllowSaleCompletingProductsException("要买肉，请到隔壁去");
    }

    @Override
    public Vegetables getVegetables(String name) throws NotAllowSaleCompletingProductsException, NoYourNeedException {
        switch (name){
            case "青瓜": {
                return  new Cucumber();
            }
            default:{
                throw  new NoYourNeedException("没有您需要的购买的东西");
            }
        }
    }
}