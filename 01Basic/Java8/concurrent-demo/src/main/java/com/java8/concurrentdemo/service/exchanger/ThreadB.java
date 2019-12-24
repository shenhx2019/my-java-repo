package com.java8.concurrentdemo.service.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

public class ThreadB extends Thread {

    Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            List<String> list = new ArrayList<>();
            list.add(Thread.currentThread().getName()+":"+exchanger.exchange("hello,world from thread b"));
            list.add(Thread.currentThread().getName()+":"+exchanger.exchange("hello,world2 from thread b"));
            list.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
