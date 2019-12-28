package com.java8.designpatterninaction.behaviour.visitor.impl;

import com.java8.designpatterninaction.behaviour.visitor.VoteVisitor;

public class SuccessAction implements VoteVisitor {
    @Override
    public void visit(SixtyPerson person) {
        System.out.println("60后投了成功一票");

    }

    @Override
    public void visit(NinetyPerson person) {
        System.out.println("90后投了成功一票");

    }
}
