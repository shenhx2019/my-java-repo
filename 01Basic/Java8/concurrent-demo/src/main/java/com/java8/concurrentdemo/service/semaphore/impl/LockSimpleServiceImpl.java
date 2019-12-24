package com.java8.concurrentdemo.service.semaphore.impl;

import com.java8.concurrentdemo.service.semaphore.SimpleService;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shenhx
 */
public class LockSimpleServiceImpl implements SimpleService {

    private Semaphore semaphore = new Semaphore(2);
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void sayHello() {
        try {
            semaphore.acquire();
            System.out.println("Thread="+Thread.currentThread().getName()+"准备");
            System.out.println("Hello begin");
            lock.lock();
            for (int i = 0;i < 5; i++){
                System.out.println(Thread.currentThread().getName()+"打印："+(i+1));
            }
            lock.unlock();
            System.out.println("Thread="+Thread.currentThread().getName()+"结束");
            semaphore.release();
            System.out.println("Hello end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
