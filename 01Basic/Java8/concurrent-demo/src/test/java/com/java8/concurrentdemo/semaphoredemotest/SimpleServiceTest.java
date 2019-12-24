package com.java8.concurrentdemo.semaphoredemotest;

import com.java8.concurrentdemo.extthread.SimpleThread;
import com.java8.concurrentdemo.service.semaphore.SimpleService;
import com.java8.concurrentdemo.service.semaphore.impl.LockSimpleServiceImpl;
import com.java8.concurrentdemo.service.semaphore.impl.SimpleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleServiceTest {

    @Test
    void testMultiHandle(){
        SimpleService service = new SimpleServiceImpl();
        SimpleThread[] services = new SimpleThread[10];
        for (int i = 0; i < services.length; i++){
            services[i] = new SimpleThread(service);
            services[i].start();
        }
    }

    @Test
    void testSingleHandle(){
        SimpleService service = new LockSimpleServiceImpl();
        SimpleThread[] services = new SimpleThread[10];
        for (int i = 0; i < services.length; i++){
            services[i] = new SimpleThread(service);
            services[i].start();
        }
    }
}
