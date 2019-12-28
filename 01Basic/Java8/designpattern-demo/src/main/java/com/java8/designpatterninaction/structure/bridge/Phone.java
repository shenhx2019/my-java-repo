package com.java8.designpatterninaction.structure.bridge;

/**
 * 充当桥的角色
 */
public abstract class Phone {
    private PhoneBrand brand;

    public Phone(PhoneBrand brand) {
        this.brand = brand;
    }

    public void call(){
        this.brand.call();
    }
}
