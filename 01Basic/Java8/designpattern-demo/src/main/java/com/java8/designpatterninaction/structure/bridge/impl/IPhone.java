package com.java8.designpatterninaction.structure.bridge.impl;

import com.java8.designpatterninaction.structure.bridge.PhoneBrand;

/**
 *
 * @author shenhx
 */
public class IPhone implements PhoneBrand {
    @Override
    public void call() {
        System.out.println("使用Iphone打电话");
    }
}
