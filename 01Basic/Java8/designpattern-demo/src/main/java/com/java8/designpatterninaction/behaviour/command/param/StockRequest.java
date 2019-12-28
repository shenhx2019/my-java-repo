package com.java8.designpatterninaction.behaviour.command.param;

public class StockRequest {
    String no;
    int quantity;


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
