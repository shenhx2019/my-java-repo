package com.java8.paralleldemo;

import com.java8.parallel.logic.MySpliteratorText;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@SpringBootTest
public class SpliteratorDemoTest {

    @Test
    void test(){
        Consumer<Integer> consumer = System.out::println;
        IntStream.rangeClosed(1,10)
                .spliterator()
                .forEachRemaining(consumer);
    }

    private final String text = "hello\nworld\njava8\nlambda\nstream";

    @Test
    void testMyStringSpliterator(){
        MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
        Optional.ofNullable(mySpliteratorText.stream().count()).ifPresent(System.out::println);

    }

}
