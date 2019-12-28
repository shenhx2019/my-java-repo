package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.behaviour.interpreter.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class InterpreterPatternTest {

    @Test
    void test(){
        Calculator calculator = new Calculator("a+b+c-d");
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("a",1);
        hashMap.put("b",2);
        hashMap.put("c",3);
        hashMap.put("d",4);
        int runResult = calculator.run(hashMap);
        assert runResult == 2;
    }

}
