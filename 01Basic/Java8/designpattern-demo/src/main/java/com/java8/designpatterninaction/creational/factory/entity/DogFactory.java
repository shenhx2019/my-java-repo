package com.java8.designpatterninaction.creational.factory.entity;

/**
 * DogFactory
 */
public class DogFactory {

    public Dog getDog(String name){
        if(name == "bq"){
            return new BaiQieDog();
        }else{
            return new ShaoKaoDog();
        }
    }
    
}