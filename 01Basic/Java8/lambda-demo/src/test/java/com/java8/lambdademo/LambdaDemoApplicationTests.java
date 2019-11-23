package com.java8.lambdademo;

import com.java8.lambdademo.entity.Apple;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

@SpringBootTest
class LambdaDemoApplicationTests {

    // 测试一： functional interface
    @FunctionalInterface
    interface MathOperation {
        int operate(int a,int b);

        // int operte2(double a,double c); // error, 只允许有一个抽象方法，但是可以允许有其他默认方法
        default void sayHello(String msg) {
            System.out.println("hello," + msg);
        }
    }

    interface  WriteLogOperation {
        void  writeTxt(String msg);
    }

    /**
     * 函数式测试
     */
    @Test
    void functionalInterfaceTest() {
        MathOperation add = (a,b) -> a+b;
        int c = add.operate(1,3);
        Assert.isTrue(4==c,"3+1=4");
    }

    @Test
    void basicFunctionalInterfaceTest() {
        Consumer<String> a = s -> {
            System.out.println(s);
            // no return value
        };
        Predicate<String> p = s -> s.isEmpty(); // 返回Boolean
        Supplier<Apple> apple = Apple::new; // 返回T的类型
        Apple a1 = apple.get();// 注意：两次获取一个get返回的对象不是一个对象
        a1.setColor("yellow");
        System.out.println(a1.getColor());
        Assert.isTrue("yellow".equals(a1.getColor()),"get和set不是同一个对象");
        Function<Integer,Double> f = i -> i * 1.0;
        Double s = f.apply(3);
        Assert.isTrue(3.0==s,"3.0！=3.0");
    }

    /**
     * 方法引用测试
     */
    @Test
    void methodReferenceTest() {
        // 原始实现：内部类
        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getColor().compareTo(a2.getColor());
            }
        };
        List<Apple> list = Collections.emptyList();
        list.sort(byColor);
        // 使用Lambda表达式
        Comparator<Apple> byColor2 = (a1,a2) -> a1.getColor().compareTo(a2.getColor());
        list.sort(byColor2);
        // 更加简便写法
        Comparator<Apple> byColor3 = Comparator.comparing(a -> a.getColor());
        Comparator<Apple> byColor4 = Comparator.comparing(Apple::getColor);
    }

    private static Apple testBiFunction(String color, int weight, BiFunction<String, Integer, Apple> fun) {
        return fun.apply(color, weight);
    }

    @Test
    void testBiFunction() {
        // 待明白这样写法的作用
        Apple a = testBiFunction("red", 3, (s,b) -> new Apple(s,b));
        // Assert.isTrue(3 == a.getWeight());
    }

    // 方法推导
    @Test
    void testMethodReference2(){
        useConsumer(s -> System.out.println(s),"hello,world");
        useConsumer(System.out::println, "hello,world");
        
    }

    /**
     * static <T> 结构表示自动推导出T的类型
     * @param consumer
     * @param t
     * @param <T>
     */
    private static <T> void  useConsumer(Consumer<T> consumer, T t){
        consumer.accept(t);
    }
}
