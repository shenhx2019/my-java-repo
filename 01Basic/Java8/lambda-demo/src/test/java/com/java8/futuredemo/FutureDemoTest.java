package com.java8.futuredemo;

import com.java8.future.logic.MySimpleFuture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@SpringBootTest
public class FutureDemoTest {

    /**
     * jdk自带的future
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    void knowHowToUseJdkFutureTest() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(10000);
                return "finish";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        while (!future.isDone()){ // 这个目前有问题
            Thread.sleep(10);
        }
        String s = future.get();
        System.out.println(s);
        service.shutdown();
    }

    @Test
    void testAsyncFuture() throws InterruptedException {
        MySimpleFuture.Future<String> future = MySimpleFuture.invokeAsync(()->{
            try {
                Thread.sleep(10000L);
                return "finished";
            } catch (InterruptedException e) {
                return "error --> " + e.getMessage();
            }
        });
        future.setCompletable(new MySimpleFuture.Completable<String>() {
            @Override
            public void complete(String value) {
                System.out.println("异步返回（成功）："+ value);
            }

            @Override
            public void exception(Throwable throwable) {
                System.out.println("异步返回（失败）："+ throwable.getMessage());
            }
        });
        System.out.println("开始输出结果：");
        while (!future.isDone()){
            Thread.sleep(100); // 等待异步线程完成
        }
        // Thread.sleep(2000);// 再等1秒看看
        Optional.ofNullable(future.get()).ifPresent(System.out::println);
    }

    /**
     * 随机数
     */
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    /**
     * 练习CompletableFuture
     * @throws InterruptedException
     */
    @Test
    void testCompletableFuture() throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executorService = Executors.newFixedThreadPool(2, t -> {
           Thread t1 = new Thread(t);
            t1.setDaemon(true);
            System.out.println(Thread.currentThread().getName());
           return t1;
        });
        CompletableFuture.supplyAsync(FutureDemoTest::get, executorService).whenComplete((t,e) -> {
            Optional.ofNullable(t).ifPresent(System.out::println);
            Optional.ofNullable(e).ifPresent(System.out::println);
            finished.set(true);
            System.out.println("supplyAsync"+finished.get());
        });
        System.out.println("no block task");
        System.out.println(finished.get());
//        while (!finished.get()){
//            Thread.sleep(10);
//        }
    }

    public static Double get(){
        try {
            System.out.println("hello,get");
            Integer i = RANDOM.nextInt(100000);
            System.out.println(i);
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }

    /**
     * 练习CompletableFuture和ThenApply
     * @throws InterruptedException
     */
    @Test
    void testCompletableFutureAndThenBy() throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return RANDOM.nextDouble();
        }, Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            System.out.println("block me?");
            return t;
        })).thenApply(FutureDemoTest::multiply).whenComplete((v,t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(System.out::println);
            finished.set(true);
        });
        System.out.println("block me");
        /*while (!finished.get()){
            Thread.sleep(100);
        }*/
    }

    private static Double multiply(Double value) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return value * 10L;
    }

    /**
     * 案例练习：
     */
    @Test
    void testProductAndShops(){
        ExecutorService executor = Executors.newFixedThreadPool(2, r ->{
            Thread t = new Thread(r);
            System.out.println(Thread.currentThread().getName());
            t.setDaemon(false);
            return t;
        });
        List<Integer> productIds = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Double> productList = productIds.stream().map(i -> CompletableFuture.supplyAsync(() -> getProductById(i), executor))
                .map(f -> f.thenApply(FutureDemoTest::multiply))
                .map(CompletableFuture::join) // 阻塞
                .collect(Collectors.toList());
        Optional.ofNullable(productList).ifPresent(System.out::println);
    }

    private static double getProductById(Integer i) {
        System.out.println("getProductById-->"+Thread.currentThread().getName());
        return i * RANDOM.nextDouble();
    }

}
