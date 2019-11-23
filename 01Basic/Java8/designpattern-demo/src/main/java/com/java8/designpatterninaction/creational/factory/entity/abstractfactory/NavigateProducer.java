package com.java8.designpatterninaction.creational.factory.entity.abstractfactory;

/**
 * @author shenhx
 */
public class NavigateProducer {

    public  static  FastFoodStoreAbstractFactory getFactory(String choice) throws NoYourNeedException {
        switch (choice){
            case "肉":{
                return  new MeatStore();
            } case "蔬菜":{
              return  new VegetablesStore();
            }
            default:{
                throw new NoYourNeedException("暂时还没提供您需要的产品类型");
            }
        }
    }

}
