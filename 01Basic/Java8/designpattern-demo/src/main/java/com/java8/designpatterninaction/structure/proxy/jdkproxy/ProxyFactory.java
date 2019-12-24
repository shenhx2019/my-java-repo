package com.java8.designpatterninaction.structure.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回动态代理子对象
     *
     * @return
     */
    public Object getFactoryInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk 代理调用开始");
                        Object returnVal = method.invoke(target, args);
                        System.out.println("jdk 代理调用结束");
                        return returnVal;
                    }
                });
    }

}
