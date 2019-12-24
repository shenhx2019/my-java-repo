package com.java8.concurrentdemo.exchangerdemotest;

import com.java8.concurrentdemo.service.exchanger.ThreadA;
import com.java8.concurrentdemo.service.exchanger.ThreadB;
import com.java8.concurrentdemo.service.exchanger.ThreadC;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Exchanger;

@SpringBootTest
public class SimpleServiceTest {

    @Test
    void testExchanger(){
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        ThreadC c = new ThreadC(exchanger);
        a.start();
        b.start();
        c.start();
    }

}
