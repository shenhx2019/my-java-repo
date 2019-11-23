package com.java8.designpatterninaction.creational.builder;

/**
 * 使用场景
 */
public class SimpleBuilderDirector {

    public SimpleContainer buildBigSizeContainer(BigSizeContainerBuilder bigSizeContainerBuilder) {
//        bigSizeContainerBuilder.addHeight(100d);
//        bigSizeContainerBuilder.addWidth(299d);
//        bigSizeContainerBuilder.build();
//        return bigSizeContainerBuilder.get();
        return bigSizeContainerBuilder.addWidth(100d).addWidth(100d).build().get();
    }
}
