package com.java8.concurrentdemo.service.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ThreadC extends Thread {

    Exchanger<String> exchanger;

    public ThreadC(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            List<String> list = new ArrayList<>();
            list.add(Thread.currentThread().getName()+":"+exchanger.exchange("hello,world from thread c"));
            list.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
