package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

/**
 * 同行竞争限制
 * @author shenhx
 */
public class NotAllowSaleCompletingProductsException extends Throwable {
    public NotAllowSaleCompletingProductsException(String s) {
    }
}
