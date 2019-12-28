package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.structure.decorator.Coffee;
import com.java8.designpatterninaction.structure.decorator.Decorator;
import com.java8.designpatterninaction.structure.decorator.Drink;
import com.java8.designpatterninaction.structure.decorator.impl.Chocolate;
import com.java8.designpatterninaction.structure.decorator.impl.Milk;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DecoratorPatternTest {

    @Test
    void  testOrderCoffee(){
        System.out.println("客人进来了，开始点餐");
        // 主体
        Drink order = new Coffee(); // 点了一杯普通咖啡
        System.out.println("系统提示：" + order.getDescription());
        // 配料
        order = new Milk(order);
        System.out.println("系统提示：" + order.getDescription());
        order = new Chocolate(order);
        System.out.println("系统提示：" + order.getDescription());
        System.out.println("客人说点完了，埋单");
        System.out.println("系统提示：点了以下东西：" + order.getDescription() + ", 总共" + order.getCost() + "钱");

    }

}
