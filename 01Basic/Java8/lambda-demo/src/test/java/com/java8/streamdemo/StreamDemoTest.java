package com.java8.streamdemo;


import com.java8.streamdemo.entity.DishEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StreamDemoTest {

    private final List<DishEntity> dishList = Arrays.asList(
            new DishEntity("番茄", true, 100, DishEntity.Type.蔬菜),
            new DishEntity("香蕉", true, 140, DishEntity.Type.水果),
            new DishEntity("鱼", false, 200, DishEntity.Type.海鲜),
            new DishEntity("牛肉", false, 700, DishEntity.Type.肉),
            new DishEntity("龙虾", false, 400, DishEntity.Type.海鲜),
            new DishEntity("鸡肉", false, 200, DishEntity.Type.肉),
            new DishEntity("兰花菜", true, 30, DishEntity.Type.蔬菜),
            new DishEntity("菜心", true, 40, DishEntity.Type.蔬菜)

            );

    List<String> getLowCaloriesDishNames(List<DishEntity> menu){
        return dishList.stream().filter(p -> p.getCalories() < 200)
                .sorted(Comparator.comparing(DishEntity::getCalories)).map(DishEntity::getName).limit(3).collect(Collectors.toList());
    }

    /**
     * 并行流处理
     * @param menu
     * @return
     */
    List<String> getLowCaloriesDishNamesParrallel(List<DishEntity> menu){
        try {
            Thread.sleep(5000, 1);
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
        return  dishList.parallelStream().filter(p -> {
            System.out.println(Thread.currentThread().getName());
            return p.getCalories() < 200;
        }).sorted(Comparator.comparing(DishEntity::getCalories)).limit(3).map(DishEntity::getName).collect(Collectors.toList());
    }

    @Test
    void testSimpleStream(){
        List<String> dishNameList = getLowCaloriesDishNames(dishList);
        dishNameList.forEach(System.out::println);
        dishNameList = getLowCaloriesDishNamesParrallel(dishList);
        dishNameList.forEach(System.out::println);
    }

    /**
     * 创建Stream 一
     */
    @Test
    void testCreateStream(){
        // 第一种方法：通过stream方法
        System.out.println("Arrays.asList");
        List<String> list = Arrays.asList("hello", "world", "java", "c#");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        // 第二种方法：通过stream.of
        System.out.println("stream.of");
        stream = Stream.of("hello", "world", "java", "c#");
        stream.forEach(System.out::println);
        // 第三种办法：通过Arrays.stream
        System.out.println("Arrays.stream");
        stream = Arrays.stream(new String[]{"hello", "world", "java", "c#"});
        stream.forEach(System.out::println);
        // 第四种办法：通过文件创建
        System.out.println("paths.get");
        // 读取URI是文件系统的路径，需要去除前缀
        Path path = Paths.get(String.valueOf(this.getClass().getClassLoader().getResource("1.txt")).replace("file:/",""));
        // System.out.println("path=" + p.getFileName());
        // Path path = Paths.get("D:\\1.txt");
        try (Stream<String> streamFromFile = Files.lines(path)){
            streamFromFile.forEach(System.out::println);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    @Test
    void testCreateStream2(){
        // 迭代器生成流
        Stream<Integer> limitIntStream = Stream.iterate(0, n -> n + 2).limit(20);
        limitIntStream.forEach(System.out::println);
        // generator函数
        Stream<Double> generateDoubleStream = Stream.generate(Math::random).limit(5);
        generateDoubleStream.forEach(System.out::println);
        // 自定义对象
        Stream<GenObj> objStream = Stream.generate(new ObjSupplier()).limit(10);
        objStream.forEach(System.out::println);
    }

    static class ObjSupplier implements Supplier<GenObj>{

        private  int index;
        private  Random random = new Random(System.currentTimeMillis());

        @Override
        public GenObj get() {
            index = random.nextInt(100);
            return new GenObj(index, "name" + index);
        }

        @Override
        public String toString() {
            return "ObjSupplier{" +
                    "index=" + index +
                    ", random=" + random +
                    '}';
        }
    }

    static class  GenObj{
        private int id;
        private String name;

        public GenObj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "GenObj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
