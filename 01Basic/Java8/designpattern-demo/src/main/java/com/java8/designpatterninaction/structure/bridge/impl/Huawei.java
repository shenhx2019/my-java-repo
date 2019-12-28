package com.java8.designpatterninaction.structure.bridge.impl;

import com.java8.designpatterninaction.structure.bridge.PhoneBrand;

/**
 * @author shenhx
 */
public class Huawei implements PhoneBrand {
    @Override
    public void call() {
        System.out.println("使用华为手机打电话");
    }
}
