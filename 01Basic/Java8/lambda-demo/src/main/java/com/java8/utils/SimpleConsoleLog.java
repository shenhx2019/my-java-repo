package com.java8.utils;

/**
 * @author shenhx
 */
public class SimpleConsoleLog {

    public  static  void  writeDebugLog(String method){
        System.out.println(Thread.currentThread().getName() + "->" + method);
    }

    public  static  void  writeLog(String method, String msg){
        System.out.println(Thread.currentThread().getName() + "->" + method + "-->" + msg);
    }

}
