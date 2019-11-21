package com.java8.designpatterninaction.creational.singleton;

import ch.qos.logback.classic.pattern.SyslogStartConverter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
public class ReentrantLockSingleton {

    private static ReentrantLockSingleton instance;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static volatile int state = 0;
    private ReentrantLockSingleton() {
    }

    public static ReentrantLockSingleton getInstance(){
        try {
            lock.lock();
            if(instance == null){
                state++;
                instance = new ReentrantLockSingleton();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            lock.unlock();
        }
        return instance;
    }

    public void test(){
        System.out.println(Thread.currentThread().getName() + "-->" +state);
        System.out.println(this.getClass().getName());
    }

}
