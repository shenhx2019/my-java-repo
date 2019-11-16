package com.java8.optional.entity;

import java.util.Optional;

/**
 * 车
 * @author shenhx
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
