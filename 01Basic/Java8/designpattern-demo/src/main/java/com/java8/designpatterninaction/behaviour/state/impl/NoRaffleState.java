package com.java8.designpatterninaction.behaviour.state.impl;

import com.java8.designpatterninaction.behaviour.state.ActivityState;
import com.java8.designpatterninaction.behaviour.state.RaffleActivity;

public class NoRaffleState extends ActivityState {

    private RaffleActivity activity;

    public NoRaffleState(RaffleActivity raffleActivity) {
        this.activity = raffleActivity;
    }

    /**
     * 扣除积分
     */
    @Override
    public void deductMoney() {
        System.out.println("系统做了积分扣除，可以抽奖一次");
        activity.setState(activity.getRegisterState(RaffleState.class));
    }

    @Override
    public boolean raffle() {
        System.out.println("系统要扣除对应的积分才允许抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能发奖");
    }
}
