package com.java8.designpatterninaction.behaviour.command;


import com.java8.designpatterninaction.behaviour.command.param.StockRequest;

/**
 * @author shenhx
 */
public abstract class Order {
    /**
     * 执行命令
     */
    public abstract void execute();


    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
