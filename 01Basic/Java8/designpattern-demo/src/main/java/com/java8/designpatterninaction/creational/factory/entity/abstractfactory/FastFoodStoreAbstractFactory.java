package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

/**
 * 菜市场定义
 */
public abstract class FastFoodStoreAbstractFactory {

    /**
     * 获取售卖肉类
     * @return
     */
    public abstract Meat getMeat(String name) throws NotAllowSaleCompletingProductsException, NoYourNeedException;

    /**
     * 获取售卖蔬菜
     * @return
     */
    public abstract Vegetables getVegetables(String name) throws NotAllowSaleCompletingProductsException, NoYourNeedException;
}