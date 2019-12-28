package com.java8.designpatterninaction.behaviour.interpreter.impl;

import com.java8.designpatterninaction.behaviour.interpreter.Expression;

import java.util.HashMap;

public class AddSymbolExpression extends SymbolExpression {

    public AddSymbolExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return this.leftExpression.interpreter(var) + this.rightExpression.interpreter(var);
    }
}
