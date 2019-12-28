package com.java8.designpatterninaction.behaviour.visitor;

import com.java8.designpatterninaction.behaviour.visitor.impl.NinetyPerson;
import com.java8.designpatterninaction.behaviour.visitor.impl.SixtyPerson;

public interface VoteVisitor {

    void visit(SixtyPerson person);
    void visit(NinetyPerson person);

}
