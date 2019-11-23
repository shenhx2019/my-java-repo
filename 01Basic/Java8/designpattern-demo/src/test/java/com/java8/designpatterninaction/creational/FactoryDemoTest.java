package com.java8.designpatterninaction.creational;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * FactoryDemoTest
 */
@SpringBootTest
public class FactoryDemoTest {

    /**
     * 测试抽象工厂模式
     */
    @Test
    void testAbstractFactoryPattern(){
        /*
        * 小王的今天的任务是：他想买一只清远鸡和一只白切狗，他要怎么买？ 
        */
       /* Map<String,Class> buyList = new HashMap<>();
        // 需要购买清单
        buyList.put("chicken", QingyuanChicken.class);
        buyList.put("dog", BaiQieDog.class);

        IAnimalFactory east = new VegetablesStore();
        IAnimalFactory west = new WestFastFoodStore();
        System.out.println("进来菜市场东");
        // 先去菜市场东看看有没有
        if(east.getChicken().getClass() == buyList.get("chicken")){
            System.out.print("成功在菜市场买到一只鸡");
            buyList.remove("chicken");
        }
        if(east.getDog().getClass() == buyList.get("dog")){
            System.out.print("成功在菜市场买到一只鸡");
            buyList.remove("dog");
        }
        if(buyList.size() <=0){
            System.out.println("成功买到，回去");
        }else{
            System.out.println("没有买全，去菜市场西看看");
        }
        // 菜市场西
        if(west.getChicken().getClass() == buyList.get("chicken")){
            System.out.println("成功在菜市场西买到一只鸡");
            buyList.remove("chicken");
        }
        if(west.getDog().getClass() == buyList.get("dog")){
            System.out.println("成功在菜市场西买到一只狗");
            buyList.remove("dog");
        }
        if(buyList.size() <=0){
            System.out.println("成功买到，回去");
        }else{
            System.out.println("没有买全，不买了");
        }*/

    }
    
}