package com.java8.designpatterninaction.creational.singleton;

/**
 * @author shenhx
 */
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

    public synchronized static DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public void test(){
        System.out.println(this.getClass().getName());
    }

}
