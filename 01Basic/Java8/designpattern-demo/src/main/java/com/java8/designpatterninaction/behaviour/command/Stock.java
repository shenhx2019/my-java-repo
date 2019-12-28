package com.java8.designpatterninaction.behaviour.command;

public class Stock {

    private String no;
    private int quantity;

    public Stock(String no) {
        this.no = no;
    }

    public void  buy(int quantity){
        this.quantity += quantity;
        System.out.println("股票代码为："+this.no+"成功买入" + quantity+"股");
    }

    public void sell(int quantity){
        if(this.quantity < quantity){
            System.out.println("超出最大能卖出的股票数，当前持股数："+ this.quantity);
            return;
        }
        this.quantity -= quantity;
        System.out.println("股票代码为："+this.no+"成功卖出" + quantity+"股");
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
