package com.java8.parallel.logic;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shenhx
 */
public class MyIntRecursiveTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final int[] data;
    private final int limit = 3;

    public MyIntRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        if((end - start) <= limit){
            int result = 0;
            for (int i = start;i < end;i++){
                result += data[i];
            }
            return result;
        }
        int mid = (end + start) / 2;
        MyIntRecursiveTask leftTask = new MyIntRecursiveTask(start, mid , data);
        MyIntRecursiveTask rightTask = new MyIntRecursiveTask(mid, end , data);
        leftTask.fork();
        int rightResult = rightTask.compute();
        int leftResult = leftTask.join();
        return rightResult + leftResult;
    }
}
