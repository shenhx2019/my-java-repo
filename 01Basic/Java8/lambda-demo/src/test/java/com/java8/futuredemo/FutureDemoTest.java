package com.java8.futuredemo;

import com.java8.future.logic.MySimpleFuture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.*;

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

}
