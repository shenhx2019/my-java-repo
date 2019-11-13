package com.java8.streamdemo.entity;


/**
 * @author Administrator
 */
public class DishEntity {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum  Type{
        蔬菜,水果,肉,海鲜,其他
    }

    public DishEntity(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return "DishEntity{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
}
