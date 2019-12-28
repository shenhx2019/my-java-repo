package com.java8.designpatterninaction.behaviour.interpreter;

import com.java8.designpatterninaction.behaviour.interpreter.impl.AddSymbolExpression;
import com.java8.designpatterninaction.behaviour.interpreter.impl.MinusSymbolExpression;
import com.java8.designpatterninaction.behaviour.interpreter.impl.VarExpression;

import java.util.HashMap;
import java.util.Stack;

/**
 * 计算器类
 */
public class Calculator {

    private Expression expression;
    /**
     *
     * @param strExp 输入 a + b -c 这种，其中后续的变量需要替换成值进行计算
     */
    public Calculator(String strExp) {
        // 存储计算的堆栈
        Stack<Expression> stack = new Stack<>();

        Expression leftExpression = null;
        Expression rightExpression = null;
        char[] charArray = strExp.toCharArray();
        for (int i = 0; i < charArray.length;i++){
            switch (charArray[i]){
                case '+':{
                    leftExpression = stack.pop();
                    rightExpression = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddSymbolExpression(leftExpression, rightExpression));
                    break;
                }
                case '-':{
                    leftExpression = stack.pop();
                    rightExpression = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new MinusSymbolExpression(leftExpression, rightExpression));
                    break;
                }
                default:{
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
                }
            }
        }
        this.expression = stack.pop();
    }



    public int run(HashMap<String, Integer> var){
        return this.expression.interpreter(var);
    }
}
