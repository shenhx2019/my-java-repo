package com.java8.designpatterninaction.behaviour.interpreter;

import java.util.HashMap;

/**
 * 表达式
 * @author shenhx
 */
public abstract class Expression {

    public abstract int interpreter(HashMap<String, Integer> var);
}
