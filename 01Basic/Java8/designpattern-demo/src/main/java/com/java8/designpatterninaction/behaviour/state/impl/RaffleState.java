package com.java8.designpatterninaction.behaviour.state.impl;

import com.java8.designpatterninaction.behaviour.state.ActivityState;
import com.java8.designpatterninaction.behaviour.state.RaffleActivity;

import java.util.Random;

public class RaffleState extends ActivityState {

    private RaffleActivity activity;

    public RaffleState(RaffleActivity raffleActivity) {
        this.activity = raffleActivity;
    }

    /**
     * 扣除积分
     */
    @Override
    public void deductMoney() {
        System.out.println("正在抽奖中，不允许扣除积分");
    }

    @Override
    public boolean raffle() {
        Random random = new Random();
        int randVal = random.nextInt(100);
        if(randVal % 2 == 0){
            System.out.println("恭喜，中奖了");
            activity.setState(activity.getRegisterState(DispensePrizeState.class));
            return true;
        }else{
            System.out.println("抱歉，没有中奖");
            activity.setState(activity.getRegisterState(NoRaffleState.class));
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("还没到发奖环节");
    }
}
