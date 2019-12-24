package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.structure.proxy.cglibproxy.ProxyFactory;
import com.java8.designpatterninaction.structure.proxy.cglibproxy.TargetObject;
import com.java8.designpatterninaction.structure.proxy.jdkproxy.ITargetObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Proxy;

@SpringBootTest
 class ProxyPatternTest {

    // cglib proxy client
    @Test
    void testCglib(){
        TargetObject targetObject = new TargetObject();
        TargetObject targetProxyInstance = (TargetObject) new ProxyFactory(targetObject).getFactoryInstance();
        targetProxyInstance.operation();
    }

    // jdk proxy client
    @Test
    void testJdkProxy(){
        ITargetObject targetObject = new com.java8.designpatterninaction.structure.proxy.jdkproxy.TargetObject();
        ITargetObject proxyFactory = (ITargetObject) new com.java8.designpatterninaction.structure.proxy.jdkproxy.ProxyFactory(targetObject).getFactoryInstance();
        proxyFactory.operation();
    }

    // 暂缺
    // spring proxy
    void testSpringProxy(){

    }

}
