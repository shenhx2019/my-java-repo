package com.java8.designpatterninaction.behaviour.state.impl;

import com.java8.designpatterninaction.behaviour.state.ActivityState;
import com.java8.designpatterninaction.behaviour.state.RaffleActivity;

import java.util.Random;

public class DispensePrizeState extends ActivityState {

    private RaffleActivity activity;

    public DispensePrizeState(RaffleActivity raffleActivity) {
        this.activity = raffleActivity;
    }

    /**
     * 扣除积分
     */
    @Override
    public void deductMoney() {
        System.out.println("发奖中，不允许扣除积分");
    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrize() {
        Random random = new Random();
        int randVal = random.nextInt(20);
        if(randVal % 2 == 0){
            System.out.println("抱歉，奖品发完了");
            activity.setState(activity.getRegisterState(DispenseOutState.class));
        }else{
            activity.setState(activity.getRegisterState(NoRaffleState.class));
            System.out.println("恭喜，奖品已经发了");
        }
    }
}
