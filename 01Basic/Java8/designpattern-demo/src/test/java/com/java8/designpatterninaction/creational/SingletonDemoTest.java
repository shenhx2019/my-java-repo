package com.java8.designpatterninaction.creational;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class SingletonDemoTest {

    private volatile static Double d = 0d;


    @Test
    void simpleDoSomeThing(){
        doSomeThing();
    }

    @Test
    synchronized void testSynchronizeMethod(){
        doSomeThing();
    }

    @Test
    void testSynchronizeBlock(){
        for (int i =0;i < 10;i++){
            Thread t = new Thread(() -> {
                synchronized (SingletonDemoTest.class){
                    System.out.println(d++);
                }
            });
            t.start();
        }
    }

    private static void doSomeThing(){
        for (int i =0;i < 10;i++){
            Thread t = new Thread(() -> {
                System.out.println(d++);
            });
            t.start();
        }
    }

}
