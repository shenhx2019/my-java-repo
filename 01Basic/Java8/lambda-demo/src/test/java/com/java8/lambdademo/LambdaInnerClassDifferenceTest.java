package com.java8.lambdademo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author shenhx
 * Lambda和内部类的区别
 */
@SpringBootTest
public class LambdaInnerClassDifferenceTest {
    private String name = "hello,outer";

    @Test
    public void InnerClassTest1() {
        final String name = "hello,function";

        class TestSuperThisOnInnerClass {
            public final String name = "hello,inner";

            public void innerTest() {
//                内部类默认会创建唯一标识的新对象，并且带有作用域，例如下面
//                super.name // 获取不到
//                this.name  // 获取不到
                String name = "inside";
                System.out.println(LambdaInnerClassDifferenceTest.this.name); // hello,outer
                System.out.println(this.name); // hello,inner
                System.out.println(TestSuperThisOnInnerClass.this.name);// hello,inner

            }
        }

        TestSuperThisOnInnerClass a = new TestSuperThisOnInnerClass();
        a.innerTest();
    }

}
