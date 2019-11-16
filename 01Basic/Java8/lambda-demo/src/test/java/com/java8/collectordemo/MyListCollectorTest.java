package com.java8.collectordemo;

import com.java8.collector.logic.MyListCollector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@SpringBootTest
public class MyListCollectorTest {

    @Test
    void test(){
        System.out.println("MyListCollectorTest-test");
        Collector<String, List<String>, List<String>> collector = new MyListCollector<>();
        String[] arr = new String[]{"java","hello","collector","3","3333","world"};
        List<String> list = Arrays.stream(arr).filter(p-> p.length() > 2).collect(collector);
        Optional.ofNullable(list).ifPresent(System.out::println);
        System.out.println("并行版本：");
        List<String> list2 =  Arrays.asList(arr).stream().filter(p-> p.length()>2).filter(p-> p.length() < 5).collect(collector);
        Optional.ofNullable(list2).ifPresent(System.out::println);
    }

}
