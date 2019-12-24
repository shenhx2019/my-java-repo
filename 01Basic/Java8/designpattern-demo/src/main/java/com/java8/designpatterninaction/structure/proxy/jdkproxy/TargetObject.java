package com.java8.designpatterninaction.structure.proxy.jdkproxy;

/**
 * cglib代理的目标对象，可以不显示继承其他类或实现任何接口
 * @author shenhx
 */
public class TargetObject  implements ITargetObject{

    @Override
    public void  operation(){
        System.out.println("TargetObject -> operation is invoked ");
    }

}
