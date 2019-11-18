package com.java8.futuredemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 *
 */
@SpringBootTest
public class CompletableFutureDemoTest {

    @Test
    void testCompletableFutureApi1() {
        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10)).whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
        }).thenRun(System.out::println);
    }

    @Test
    void testCompletableFutureApi2(){
        CompletableFuture.supplyAsync(() -> 1)
                .thenAccept(System.out::println);
    }

    @Test
    void testCompletableFutureApi3(){
        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 2))
                .thenAccept(System.out::println);
    }

    @Test
    void testCompletableFutureApi4(){
        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2),(r1,r2) -> r1+r2)
                .thenAccept(System.out::println);
    }

    @Test
    void testCompletableFutureApi5(){
        CompletableFuture<Void> result = CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> {
                    System.out.println(r1 + r2);
                }) // 返回CompletableFuture<Void>
                .thenAccept(System.out::println);// 输出null
        /*System.out.println(result);
        System.out.println(result.getClass());
        assert null == result;*/
    }
    /**
     * 10. runAfterBoth
     * 11. applyToEither(两者之一)
     * 12. acceptEither
     * 13. runAfterEither
     */
    @Test
    void testCompletableFutureApi6() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 2;
        }), () -> System.out.println("done"));
    }

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @Test
    void testLoopOfCompletableFutureApi7() throws InterruptedException {
        for (int i = 0; i< 10;i++){
            testCompletableFutureApi7();
            System.out.println("-----");
        }
        // Thread.currentThread().join();
    }

    @Test
    void testCompletableFutureApi7() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1-->"+Thread.currentThread().getName());
            return 1;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2-->"+Thread.currentThread().getName());
            return 2;
        }), i -> {
            return i * 2;
        }).thenAccept(System.out::println);
        // Thread.currentThread().join();
    }

    @Test
    void testCompletableFutureApi8() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Integer i = RANDOM.nextInt(100);
                System.out.println(i);
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1-->"+Thread.currentThread().getName());
            return 1;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Integer i = RANDOM.nextInt(100);
                System.out.println(i);
                Thread.sleep(RANDOM.nextInt(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2-->"+Thread.currentThread().getName());
            return 2;
        }), i -> System.out.println(i * 2));
        Thread.currentThread().join();
    }

    @Test
    void testCompletableFutureApi9(){
        List<CompletableFuture<Double>> result = Arrays.asList(1, 2, 3).stream().map(i -> CompletableFuture.supplyAsync(FutureDemoTest::get))
                .collect(Collectors.toList());
//        CompletableFuture.allOf(result.toArray(new CompletableFuture[result.size()]))
//                .thenAccept(System.out::println)
//                .thenRun(() -> System.out.println("done"));

        CompletableFuture.anyOf(result.toArray(new CompletableFuture[result.size()]))
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("done"));
    }

}
