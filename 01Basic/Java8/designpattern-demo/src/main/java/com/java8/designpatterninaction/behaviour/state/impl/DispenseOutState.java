package com.java8.designpatterninaction.behaviour.state.impl;

import com.java8.designpatterninaction.behaviour.state.ActivityState;
import com.java8.designpatterninaction.behaviour.state.RaffleActivity;

public class DispenseOutState extends ActivityState {

    private RaffleActivity activity;

    public DispenseOutState(RaffleActivity raffleActivity) {
        this.activity = raffleActivity;
    }

    /**
     * 扣除积分
     */
    @Override
    public void deductMoney() {
        System.out.println("不允许操作，请下次参与");
    }

    @Override
    public boolean raffle() {
        System.out.println("不允许操作，请下次参与");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不允许操作，请下次参与");
    }
}
