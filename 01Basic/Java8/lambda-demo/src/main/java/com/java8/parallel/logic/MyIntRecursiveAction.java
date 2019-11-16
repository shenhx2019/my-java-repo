package com.java8.parallel.logic;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * fork/join 练习
 * @author shenhx
 */
public class MyIntRecursiveAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final int[] data;
    private final int limit = 3;

    private MyIntRecursiveAction(int start, int end, int[] data, boolean firstInit) {
        this.start = start;
        this.end = end;
        this.data = data;
        if(firstInit){
            AccurateHelper.reset();
        }
    }

    public MyIntRecursiveAction(int start, int end, int[] data) {
        this(start,end,data, true);
    }

    @Override
    protected void compute() {
        if((end - start) <= limit){
            for (int i = start;i < end;i++){
                AccurateHelper.setResult(data[i]);
            }
        }else{
            int mid =( start+end)/2;
            MyIntRecursiveAction leftAction = new MyIntRecursiveAction(start,mid,data,false);
            MyIntRecursiveAction rightAction = new MyIntRecursiveAction(mid,end,data,false);
            leftAction.fork();
            rightAction.fork();
            leftAction.join();
            rightAction.join();
        }
    }

    /**
     * 使用内部类持有累积计算结果
     */
    public static class AccurateHelper {
        public static final AtomicInteger result = new AtomicInteger(0);
        static void setResult(int value){
            result.addAndGet(value);
        }
        public static int getResult(){
            return result.get();
        }

        static void reset(){
            result.set(0);
        }

    }
}
