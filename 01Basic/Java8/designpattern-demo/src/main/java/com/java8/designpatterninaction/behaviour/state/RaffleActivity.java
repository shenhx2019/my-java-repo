package com.java8.designpatterninaction.behaviour.state;

import com.java8.designpatterninaction.behaviour.state.impl.DispenseOutState;
import com.java8.designpatterninaction.behaviour.state.impl.DispensePrizeState;
import com.java8.designpatterninaction.behaviour.state.impl.NoRaffleState;
import com.java8.designpatterninaction.behaviour.state.impl.RaffleState;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 活动上下文对象
 * @author shenhx
 */
public class RaffleActivity {

    private final HashMap<Type, ActivityState> stateMap = new HashMap<>(); 
    
    public RaffleActivity() {
        // 注册登陆当前活动的状态
        stateMap.put(NoRaffleState.class, new NoRaffleState(this));
        stateMap.put(RaffleState.class, new RaffleState(this));
        stateMap.put(DispensePrizeState.class, new DispensePrizeState(this));
        stateMap.put(DispenseOutState.class, new DispenseOutState(this));
        // 设置成开始状态
        this.setState(stateMap.get(NoRaffleState.class));
    }
    
    public ActivityState getRegisterState(Type stateType){
        return stateMap.get(stateType);
    }

    /**
     * 当前状态
     */
    private ActivityState state;

    public ActivityState getState() {
        return state;
    }

    public void setState(ActivityState state) {
        this.state = state;
    }

    public  void deductMoney(){
        this.state.deductMoney();
    }
    public  boolean raffle(){
        return this.state.raffle();
    }
    public  void dispensePrize(){
        this.state.dispensePrize();
    }

}
