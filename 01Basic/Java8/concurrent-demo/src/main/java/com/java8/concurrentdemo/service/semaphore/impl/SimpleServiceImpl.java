package com.java8.concurrentdemo.service.semaphore.impl;

import com.java8.concurrentdemo.service.semaphore.SimpleService;

import java.util.concurrent.Semaphore;

/**
 * 多进路-多处理-多出路实践
 * @author shenhx
 */
public class SimpleServiceImpl implements SimpleService {

    private Semaphore semaphore = new Semaphore(2);

    /**
     * 测试方法
     */
    @Override
    public void sayHello(){
        try {
            semaphore.acquire();
            System.out.println("Thread="+Thread.currentThread().getName()+"准备");
            System.out.println("Hello begin");
            for (int i = 0;i < 5; i++){
                System.out.println(Thread.currentThread().getName()+"打印："+(i+1));
            }
            System.out.println("Thread="+Thread.currentThread().getName()+"结束");
            semaphore.release();
            System.out.println("Hello end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
