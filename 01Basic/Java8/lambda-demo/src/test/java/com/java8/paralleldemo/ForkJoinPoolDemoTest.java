package com.java8.paralleldemo;

import com.java8.parallel.logic.MyIntRecursiveAction;
import com.java8.parallel.logic.MyIntRecursiveTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ForkJoinPool;

@SpringBootTest
public class ForkJoinPoolDemoTest {

    private final int[] arrInts = new int[]{1,2,3,4,5,6,7,87,9,12,11,23,30,50};

    @Test
    void test1(){
        System.out.println(5/2);
    }

    @Test
    void normalLoopTest(){
        int result = 0;
        for (int i = 0;i < arrInts.length;i++){
            result += arrInts[i];
        }
        System.out.println("normalLoopTest计算结果：" + result);
    }

    @Test
    void recursiveTaskTest(){
        MyIntRecursiveTask task = new MyIntRecursiveTask(0,arrInts.length,arrInts);
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(task);
        System.out.println("recursiveTaskTest计算结果：" + result);
    }

    @Test
    void vecursiveActionTest(){
        MyIntRecursiveAction action = new MyIntRecursiveAction(0,arrInts.length,arrInts);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(action);
        Integer result = MyIntRecursiveAction.AccurateHelper.getResult();
        System.out.println(result);
        assert result == 250;
    }

}
