package com.java8.paralleldemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并发编程测试
 */
@SpringBootTest
public class ParallelProgramDemoTest {

    @Test
    void test(){
        // 8内核
        System.out.println("当前有效内核："+ Runtime.getRuntime().availableProcessors());
    }

    @Test
    void  testMeasurePerformace(){
        System.out.println("最优时间(iteratorNormal)：>" + measurePerformace(ParallelProgramDemoTest::iteratorNormal, 100_000_000) + "ms");
        // System.out.println("最优时间(iteratorStream)：>" + measurePerformace(ParallelProgramDemoTest::iteratorStream, 100_000_000) + "ms");
        // System.out.println("最优时间(iteratorParallelStream)：>" + measurePerformace(ParallelProgramDemoTest::iteratorParallelStream, 10_000_000) + "ms");
        // System.out.println("最优时间(iteratorParallelStream2)：>" + measurePerformace(ParallelProgramDemoTest::iteratorParallelStream2, 10_000_000) + "ms");
        System.out.println("最优时间(iteratorParallelStream3)：>" + measurePerformace(ParallelProgramDemoTest::iteratorParallelStream3, 100_000_000) + "ms");
        System.out.println("最优时间(iteratorParallelStream4)：>" + measurePerformace(ParallelProgramDemoTest::iteratorParallelStream4, 100_000_000) + "ms");

    }

    private long  measurePerformace(Function<Long,Long> f, long limit){
        Long fastest = Long.MAX_VALUE;
        for (long i = 0;i < 10; i++){
            Long startTime = System.currentTimeMillis();
            Long result = f.apply(limit);
            Long duration = System.currentTimeMillis() - startTime;
            // System.out.println("the result of sum => " + result);
            if(duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    private static long iteratorStream(final long t){
        return Stream.iterate(1L,i -> i+1).limit(t).reduce(0L,Long::sum);
    }

    private static long iteratorParallelStream(final long t){
        return Stream.iterate(1L,i -> i+1).parallel().limit(t).reduce(0L,Long::sum);
    }

    /**
     * 避免long到Long的装箱操作
     * @param t
     * @return
     */
    private static long iteratorParallelStream2(final long t){
        return Stream.iterate(1L,i -> i+1).mapToLong(Long::intValue).parallel().limit(t).reduce(0L,Long::sum);
    }

    private static long iteratorParallelStream3(final long t){
        return LongStream.rangeClosed(1,t).sum();
    }

    private static long iteratorParallelStream4(final long t){
        return LongStream.rangeClosed(1,t).parallel().sum();
    }

    private static long iteratorNormal(final long t){
        long result = 0L;
        for (long i = 1L;i < t;i++){
            result += i;
        }
        return result;
    }

}
