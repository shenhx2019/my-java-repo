package com.java8.designpatterninaction.creational.builder;

/**
 *
 * @author shenhx
 */
public class BigSizeContainerBuilder implements  SimpleBuilder {

    private static final SimpleContainer container = new SimpleContainer();

    @Override
    public SimpleBuilder addWidth(Double width) {
        container.setWidth(width);
        return this;
    }

    @Override
    public SimpleBuilder addHeight(Double height) {
        container.setHeight(height);
        return this;
    }

    @Override
    public SimpleBuilder build() {
        if(container.getHeight() == 0d){
            container.setHeight(40d);
        }
        if(container.getWidth() == 0d){
            container.setHeight(40d);
        }
        return this;
    }

    @Override
    public SimpleContainer get() {
        return container;
    }
}
