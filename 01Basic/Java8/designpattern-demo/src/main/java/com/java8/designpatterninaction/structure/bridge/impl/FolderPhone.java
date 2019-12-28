package com.java8.designpatterninaction.structure.bridge.impl;

import com.java8.designpatterninaction.structure.bridge.Phone;
import com.java8.designpatterninaction.structure.bridge.PhoneBrand;

public class FolderPhone extends Phone {
    public FolderPhone(PhoneBrand brand) {
        super(brand);
    }

    @Override
    public void call() {
        super.call();
        System.out.println("折叠式手机");
    }
}
