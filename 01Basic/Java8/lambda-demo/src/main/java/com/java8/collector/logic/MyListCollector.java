package com.java8.collector.logic;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author shenhx
 */
public class MyListCollector<T> implements Collector<T, List<T>,List<T>> {

    /**
     * 一个创建并返回一个新的可变结果容器的函数
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        log("supplier");
        return ArrayList::new;
    }

    /**
     * 将值折叠成可变结果容器的函数
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator");
        return List::add;
    }

    /**
     * 一个接受两个部分结果并将其合并的函数
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        log("combiner");
        return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 执行从中间累积类型 A到最终结果类型 R的最终 R
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        return list -> list;
    }

    /**
     * 返回一个 Set的 Collector.Characteristics表示该收集者的特征
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }

    private void  log(final String msg){
        System.out.println(Thread.currentThread().getName()+"--->"+msg);
    }
}
