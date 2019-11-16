package com.java8.optional.entity;

import java.util.Optional;

/**
 * 人
 * @author shenhx
 */
public class Person {
    private Optional<Car> car;

    private Optional<House> house;

    public Optional<Car> getCar() {
        return car;
    }

    public Optional<House> getHouse() {
        return house;
    }
}
