package com.java8.streamdemo;


import com.java8.streamdemo.entity.DishEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    List<String> getLowcaloriesDishNames(List<DishEntity> menu){
        return dishList.stream().filter(p -> p.getCalories() < 200)
                .sorted(Comparator.comparing(DishEntity::getCalories)).map(DishEntity::getName).collect(Collectors.toList());
    }

    @Test
    void testSimpleStream(){
        List<String> dishNameList = getLowcaloriesDishNames(dishList);
        dishNameList.forEach(System.out::println);
    }
}
