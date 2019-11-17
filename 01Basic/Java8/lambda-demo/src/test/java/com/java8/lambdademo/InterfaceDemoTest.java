package com.java8.lambdademo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InterfaceDemoTest {

    @Test
    void testFunctionalInterface(){
        A a = () -> 10;
        // A.length = 10;// error
        System.out.println(A.length);
        a.print();
        System.out.println(a.empty());
    }

    @Test
    void testInterface(){
        // B b = () -> 10; // 非函数接口不能实现lambda的赋值
        class B1 implements B {

            @Override
            public int size() {
                return 1;
            }

            @Override
            public double count() {
                return 2;
            }
        }
        // java 使用类之前一定先声明
        B b = new B1();
        System.out.println(B.age());
        System.out.println(b.empty());
        System.out.println(b.empty2());
    }

    // 基本练习
    @FunctionalInterface
    interface A {
        int size();
        Integer length = 1; // public、static、final
        default void print(){
            System.out.println(length.getClass());
            System.out.println(length);
        }
        default boolean empty(){
            return size() == 0;
        }
    }

    interface B{
        int size();
        double count();
        static int age(){
            return 1;
        }
        static int years(){
            return 2019;
        }
        default boolean empty(){
            return size() ==0;
        }
        default boolean empty2(){
            return count() ==0;
        }
    }

    // 接口继承练习

    interface C{
        default void say(){
            System.out.println("C---hello");
        }
    }

    interface D extends C{
        @Override
        default void say(){
            System.out.println("D---hello");
        }
    }

    private static class E implements C,D{
        public int a = 0;
        public int c;
        public  static int f = 0;
        static {
            f = 5;
        }
        public void say2(){
            System.out.println("hello,2");
        }
        public static void say3(){
            System.out.println("hello,3");
        }
//        @Override
//        public void say(){
//            System.out.println("E---hello");
//        }
    }

    @Test
    void testInterfaceExtend(){
        D e = new E();
        e.say();
        System.out.println(E.f); // 5

    }


}
