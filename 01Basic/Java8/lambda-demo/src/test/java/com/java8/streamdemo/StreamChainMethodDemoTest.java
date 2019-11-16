package com.java8.streamdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootTest
public class StreamChainMethodDemoTest {

    private List<Integer> list = Arrays.asList(1,3,2,4,5,7,23,2,34,53,2,24);

    /**
     * 过滤
     */
    @Test
    void  testIntegerFilter(){
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        result.forEach(System.out::println);
    }

    /**
     * 去重
     */
    @Test
    void testIntegerDistinct(){
        List<Integer> result = list.stream().distinct().filter(i -> i % 2 == 0).collect(toList());
        result.forEach(System.out::println);
    }

    /**
     * 跳过和限制，可做分页
     */
    @Test
    void  testIntegerSkipAndLimit(){
        List<Integer> result = list.stream().skip(5).limit(6).collect(toList());
        result.forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    void testIntegerMap(){
        List<Integer> result = list.stream().map(i -> i * 3).collect(toList());
        result.forEach(System.out::println);
    }

    /**
     * 扁平化映射
     */
    @Test
    void  testFlatMap(){
        String[] arrNames = new String[]{"hello","world"};
        Stream<String[]> stream = Arrays.stream(arrNames).map(s -> s.split(""));
        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        stringStream.forEach(System.out::println);
    }

    @Test
    void testMatch(){
        boolean b = list.stream().allMatch(p -> p > 2);
        assert b==false : "不是所有的都大于2";
        boolean c = list.stream().anyMatch(p -> p > 2);
        assert c: "没有一个数大于2";
        boolean d = list.stream().noneMatch(p -> p <0);
        assert d: "有数小于0";
    }

    /**
     * 测试Java8的可空对象，待熟悉
     */
    @Test
    void testOptional(){
        Optional<String> o = Optional.of("1212");
        o.ifPresent(s -> {
            System.out.println(s);
        });
        System.out.println(o.orElse("hello"));
        // assert o.get() == null : "Optional对象不可能为空";
    }

    @Test
    void  testFind(){
        Optional<Integer> opInt = list.stream().filter(p -> p > 100).findAny();
        System.out.println(opInt.get());
        System.out.println(opInt.orElse(-1));
        opInt = list.stream().filter(p -> p == 3).findFirst();
        assert opInt.get() == 3:"找不到3";
    }

    /**
     * 聚合，只有一个Optional<T>的返回值
     */
    @Test
    void testReduce(){
        Integer result = list.stream().reduce(0,(i,j) -> i + j);
        System.out.println(result);
        list.stream().reduce((i,j) -> i+j).ifPresent(System.out::println);
        list.stream().reduce(Integer::max).ifPresent(System.out::println);
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    /**
     * 开平方
     */
    @Test
    void testMath(){
        System.out.println(Math.sqrt(3*3+4*4)%1);
        System.out.println(Math.sqrt(3*3+5*5)%1);
        System.out.println(Math.sqrt(3*3+6*6)%1);

    }

    @Test
    void testStreamConvert(){
        int a = 9;
        // 第一种写法
        IntStream.rangeClosed(1,100).filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .boxed()// 装箱
                .map((b-> new int[]{a,b,(int)Math.sqrt(a*a + b*b)}))
                .forEach(r -> System.out.println("a="+r[0]+",b="+r[1]+",c="+r[2]));
        System.out.println("-----------");
        // 第二种写法
        IntStream.rangeClosed(1,100).filter(b -> Math.sqrt(a*a+b*b)%1 ==0)
                .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a*a + b*b)})
                .forEach(r -> System.out.println("a="+r[0]+",b="+r[1]+",c="+r[2]));
    }

}
