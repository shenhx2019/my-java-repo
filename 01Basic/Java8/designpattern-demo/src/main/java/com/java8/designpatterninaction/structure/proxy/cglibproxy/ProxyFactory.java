package com.java8.designpatterninaction.structure.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理-代理对象
 * @author shenhx
 */
public class ProxyFactory implements MethodInterceptor {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回动态代理子对象
     * @return
     */
    public  Object getFactoryInstance(){
        // 创建工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 返回对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //
        System.out.println("cglib 代理调用开始");
        Object returnVal = method.invoke(target, args);
        System.out.println("cglib 代理调用结束");
        return  returnVal;
    }
}
