package com.java8.designpatterninaction.creational.builder;


/**
 *
 * @author shenhx
 */
public interface SimpleBuilder {
    SimpleBuilder addWidth(Double width);
    SimpleBuilder addHeight(Double height);
    SimpleBuilder build();
    SimpleContainer get();
}
