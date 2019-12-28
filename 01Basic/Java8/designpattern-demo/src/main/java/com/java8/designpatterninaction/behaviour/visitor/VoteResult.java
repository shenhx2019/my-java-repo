package com.java8.designpatterninaction.behaviour.visitor;

import java.util.ArrayList;
import java.util.List;

public class VoteResult extends Person {

    private List<Person> personList = new ArrayList<>();

    public void attach(Person p){
        personList.add(p);
    }

    @Override
    public void accept(VoteVisitor action) {
        for (Person p : personList){
            p.accept(action);
        }
        personList.clear();
    }
}
