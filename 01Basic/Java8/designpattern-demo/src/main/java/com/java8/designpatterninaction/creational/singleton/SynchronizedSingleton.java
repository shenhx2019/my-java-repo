package com.java8.designpatterninaction.creational.singleton;

/**
 * Synchronized修饰避免实现多个实例
 * @author shenhx
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
    }

    public synchronized static SynchronizedSingleton getInstance(){
        if(instance == null){
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public void test(){
        System.out.println(this.getClass().getName());
    }
}
