package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.behaviour.state.RaffleActivity;
import com.java8.designpatterninaction.behaviour.state.impl.NoRaffleState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatePatternTest {

    @Test
    void test(){
        RaffleActivity activity = new RaffleActivity(); // 声明活动上下文
        System.out.println(String.valueOf(activity.getState().getClass()));
        activity.deductMoney();
        System.out.println(String.valueOf(activity.getState().getClass()));
        activity.raffle();
        System.out.println(String.valueOf(activity.getState().getClass()));
        activity.dispensePrize();
        System.out.println(String.valueOf(activity.getState().getClass()));
    }
}
