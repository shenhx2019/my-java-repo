package com.java8.lambdademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Consumer;

/**
 * @author shenhx
 */
@SpringBootApplication
public class LambdaDemoApplication {

    public static void main(String[] args) {
        System.out.println("hello,world");
        SpringApplication.run(LambdaDemoApplication.class, args);
    }

}
