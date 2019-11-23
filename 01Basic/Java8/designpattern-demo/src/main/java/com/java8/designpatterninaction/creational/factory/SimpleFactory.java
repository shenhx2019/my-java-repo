package com.java8.designpatterninaction.creational.factory;

import com.java8.designpatterninaction.creational.factory.entity.Dog;
import com.java8.designpatterninaction.creational.factory.entity.IAnimal;
import com.java8.designpatterninaction.creational.factory.entity.Chicken;

/**
 * SimpleFactory
 */
public class SimpleFactory {

    private enum Animal { dog, chiken}

    public IAnimal getAnimal(Animal animal) throws Exception {
        if(animal == Animal.chiken){
            return new Chicken();
        }else if(animal == Animal.dog){
            return new Dog();
        }
        throw new Exception("没有找到合适的实现类");
    }
}