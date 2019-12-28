package com.java8.designpatterninaction.behaviour.visitor.impl;

import com.java8.designpatterninaction.behaviour.visitor.VoteVisitor;

public class FailAction implements VoteVisitor {
    @Override
    public void visit(SixtyPerson person) {
        System.out.println("60后投了失败一票");

    }

    @Override
    public void visit(NinetyPerson person) {
        System.out.println("90后投了失败一票");

    }
}
