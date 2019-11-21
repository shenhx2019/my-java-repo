package com.java8.designpatterninaction.creational.factory;

import java.util.HashMap;
import java.util.Map;

import com.java8.designpatterninaction.creational.factory.entity.Chicken;
import com.java8.designpatterninaction.creational.factory.entity.Dog;
import com.java8.designpatterninaction.creational.factory.entity.IAnimal;

/**
 * ReflectSimpleFactory
 * 通过反射实现工厂类的初始化
 */
public class ReflectSimpleFactory {

    private static final Map<String, Class> registerAnimals = new HashMap<>();
    private static final Map<String, IAnimal> registerAnimals2 = new HashMap<>();
    
    static {
        register("dog", Dog.class);
        register("chiken", Chicken.class);
        registerObject("dog", new Dog());
        registerObject("chiken", new Chicken());
    }

    static void register(String key, Class c){
        registerAnimals.put(key, c);
    }

    static void registerObject(String key, IAnimal animal){
        registerAnimals2.put(key, animal);
    }

    public static IAnimal getAnimal(String animal) throws InstantiationException, IllegalAccessException {
        Class animalClass = registerAnimals.get(animal);
        return (IAnimal)animalClass.newInstance();
    }

    public static IAnimal getAnimal2(String animal) throws InstantiationException, IllegalAccessException {
        return registerAnimals2.get(animal).newInstance();
        
    }


}