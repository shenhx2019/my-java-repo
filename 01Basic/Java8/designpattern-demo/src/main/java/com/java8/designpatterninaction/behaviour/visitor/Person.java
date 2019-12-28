package com.java8.designpatterninaction.behaviour.visitor;

public abstract class Person {

    public abstract void accept(VoteVisitor action);

}
