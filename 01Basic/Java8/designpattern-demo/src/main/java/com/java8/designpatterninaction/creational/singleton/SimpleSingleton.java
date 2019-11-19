package com.java8.designpatterninaction.creational.singleton;

/**
 * 简单的单例模式
 * 就是程序在运行期间，有且只有一个实例
 * @author shenhx
 */
public class SimpleSingleton {
    private static SimpleSingleton instance;

    private SimpleSingleton() {
    }

    public static SimpleSingleton getInstance(){
        if(instance == null){
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public void test(){
        System.out.println(this.getClass().getName());
    }

}
