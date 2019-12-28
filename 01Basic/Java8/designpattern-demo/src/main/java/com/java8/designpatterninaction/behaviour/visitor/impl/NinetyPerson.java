package com.java8.designpatterninaction.behaviour.visitor.impl;

import com.java8.designpatterninaction.behaviour.visitor.Person;
import com.java8.designpatterninaction.behaviour.visitor.VoteVisitor;

public class NinetyPerson extends Person {
    @Override
    public void accept(VoteVisitor action) {
        action.visit(this);
    }
}
