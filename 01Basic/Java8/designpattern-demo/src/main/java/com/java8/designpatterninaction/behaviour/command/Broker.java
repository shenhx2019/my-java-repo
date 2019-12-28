package com.java8.designpatterninaction.behaviour.command;

import com.java8.designpatterninaction.behaviour.command.impl.NoneOrder;
import com.java8.designpatterninaction.behaviour.command.param.StockRequest;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    /**
     * 代理的股票
     */
    private final static List<Stock> PROXY_STOCK_LIST = new ArrayList<>();

    private final List<Order> orderList = new ArrayList<>();

    static {
        // 增加股票
        PROXY_STOCK_LIST.add(new Stock("1001"));
        PROXY_STOCK_LIST.add(new Stock("1002"));
        PROXY_STOCK_LIST.add(new Stock("1003"));
        PROXY_STOCK_LIST.add(new Stock("1004"));
        PROXY_STOCK_LIST.add(new Stock("1005"));
    }

    public void takeOrder(Order order, StockRequest request){
        boolean canBuyHere = PROXY_STOCK_LIST.stream().anyMatch(p -> p.getNo().equals(request.getNo()));
        if(!canBuyHere){
            orderList.add(new NoneOrder("没有代理到该股票"));
        }else {
            orderList.add(order);
        }
    }

    public void placeOrders(){
        orderList.forEach(Order::execute);
        orderList.clear();
    }

}
