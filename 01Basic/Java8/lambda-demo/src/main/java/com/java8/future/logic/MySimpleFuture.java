package com.java8.future.logic;

import com.java8.utils.SimpleConsoleLog;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shenhx
 */
public class MySimpleFuture {

    /**
     * 阻塞调用
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();
        Future<T> future = new Future<T>() {

            @Override
            public T get() {
                SimpleConsoleLog.writeLog("get","Future");
                return result.get();
            }

            @Override
            public boolean isDone() {
                SimpleConsoleLog.writeLog("isDone","Future");
                return finished.get();
            }
        };
        return future;
    }

    /**
     * 异步调用
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> invokeAsync(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<T>() {

            private Completable<T> completable = null;

            @Override
            public T get() {
                SimpleConsoleLog.writeLog("get","Future");
                return result.get();
            }

            @Override
            public boolean isDone() {
                SimpleConsoleLog.writeLog("isDone","Future");
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                SimpleConsoleLog.writeLog("setCompletable","Future");
                Objects.requireNonNull(completable);
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                SimpleConsoleLog.writeLog("getCompletable","Future");
                return this.completable;
            }
        };
        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                Completable<T> completable = future.getCompletable();
                if(completable != null){
                    completable.complete(value);
                }
            }catch (Throwable e){
                Completable<T> completable = future.getCompletable();
                if(completable != null){
                    completable.exception(e);
                }
            }

        });
        t.start();
        return future;
    }

    public interface Future<T>{
        /**
         * 获取future返回的对象
         * @return
         */
        T get();

        /**
         * 标记方法是否完成
         * @return
         */
        boolean isDone();

        /**
         * 设置完成回调对象
         * @param completable
         */
        default void setCompletable(Completable<T> completable){
        }

        /**
         * 获取完成回调对象
         * @return
         */
        default Completable<T> getCompletable(){
            return null;
        }
    }

    public interface Completable<T>{
        /**
         * 完成时候回调
         * @param value
         */
        void complete(T value);

        /**
         * 异常时回调
         * @param throwable
         */
        void exception(Throwable throwable);
    }

    public interface Callable<T>{
        /**
         * 操作线程执行返回的值
         * @return T
         */
        T action();
    }

}
