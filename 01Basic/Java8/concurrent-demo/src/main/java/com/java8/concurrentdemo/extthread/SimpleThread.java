package com.java8.concurrentdemo.extthread;

import com.java8.concurrentdemo.service.semaphore.SimpleService;

/**
 * @author shenhx
 */
public class SimpleThread extends Thread {
    public SimpleThread(SimpleService service) {
        this.service = service;
    }

    private SimpleService service;

    @Override
    public void run(){
        service.sayHello();
    }
}
