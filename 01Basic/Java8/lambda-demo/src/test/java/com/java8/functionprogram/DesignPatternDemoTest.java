package com.java8.functionprogram;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 函数式设计模式练习
 * 案例：
 * 1. MapReduce
 * 2. 借贷模式
 * 3. 尾调用优化
 * 4. 记忆化
 * 5. 执行around方法
 */
public class DesignPatternDemoTest {

    /**
     * MapReduce练习
     * 读取并计算相应的单词数量
     * TODO java 怎么访问资源还有问题
     */
    @Test
    void testMapReduce(){
        // 读取文件
        Map<String,Integer> wordCountMap = new HashMap<>();
        // String dataFilePath = String.valueOf(this.getClass().getResource("D:\DemoTest\java2019\my-java-repo\01Basic\Java8\lambda-demo\target\classes")).replace("file:/", "");
        // System.out.println(dataFilePath);
        //Path parentFilePath = Paths.get(dataFilePath).getParent();
        // System.out.println("读取的路径：" + parentFilePath.getFileName());
        try(Stream<Path> files = Files.find(Paths.get("D:\\DemoTest\\java2019\\my-java-repo\\01Basic\\Java8\\lambda-demo\\target\\classes"), 1, (path, attr) -> String.valueOf(path).endsWith(".txt"))) {
            List<String[]> collect = files.parallel()
                    .flatMap(x -> {
                        try {
                            return Files.lines(x);
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .map(x -> x.split(" "))
                    .collect(Collectors.toList());
            System.out.println(collect);
            System.out.println(wordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
