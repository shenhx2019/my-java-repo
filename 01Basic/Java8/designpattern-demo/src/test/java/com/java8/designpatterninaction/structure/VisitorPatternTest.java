package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.behaviour.visitor.VoteResult;
import com.java8.designpatterninaction.behaviour.visitor.VoteVisitor;
import com.java8.designpatterninaction.behaviour.visitor.impl.FailAction;
import com.java8.designpatterninaction.behaviour.visitor.impl.NinetyPerson;
import com.java8.designpatterninaction.behaviour.visitor.impl.SixtyPerson;
import com.java8.designpatterninaction.behaviour.visitor.impl.SuccessAction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VisitorPatternTest {

    @Test
    void test(){
        VoteResult result = new VoteResult();
        result.attach(new NinetyPerson());
        result.attach(new SixtyPerson());
        result.accept(new SuccessAction());
        result.attach(new NinetyPerson());
        result.attach(new SixtyPerson());
        result.attach(new NinetyPerson());
        result.accept(new FailAction());


    }


}
