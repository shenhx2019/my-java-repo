package com.java8.designpatterninaction.creational.singleton;

/**
 * 【推荐】只是实例化一次
 * @author shenhx
 */
public class LockFreeSingleton {

    /**
     * 只是静态实例一次
     */
    private static LockFreeSingleton instance = new LockFreeSingleton();

    private LockFreeSingleton() {
    }

    public synchronized static LockFreeSingleton getInstance(){
        return instance;
    }

    public void test(){
        System.out.println(this.getClass().getName());
    }

}
