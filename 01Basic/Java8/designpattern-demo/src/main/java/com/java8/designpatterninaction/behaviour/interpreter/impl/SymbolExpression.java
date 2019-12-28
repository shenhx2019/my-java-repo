package com.java8.designpatterninaction.behaviour.interpreter.impl;

import com.java8.designpatterninaction.behaviour.interpreter.Expression;

import java.util.HashMap;

/**
 * 符号表达式，表示终结者
 */
public class SymbolExpression extends Expression {

    protected Expression leftExpression;
    protected Expression rightExpression;

    public SymbolExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
        // return leftExpression.interpreter(var) + rightExpression.interpreter(var);
    }
}
