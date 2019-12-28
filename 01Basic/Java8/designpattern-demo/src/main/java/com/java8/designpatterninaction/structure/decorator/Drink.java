package com.java8.designpatterninaction.structure.decorator;

/**
 * 解决主体和其他多组合的问题
 */
public abstract class Drink {

    private String description;
    private float price;

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 获取花费
     * @return
     */
    public abstract double getCost();
}
