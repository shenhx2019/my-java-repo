package com.java8.optionaldemo;

import com.java8.optional.entity.Car;
import com.java8.optional.entity.House;
import com.java8.optional.entity.Insurance;
import com.java8.optional.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class OptionalDemoTest {

    @Test
    void testOptional(){
//        String insuranceName = "";
//        insuranceName = getInsuranceNameByOptional(new Person());
//        System.out.println(insuranceName);
        Optional.ofNullable(getInsuranceNameByOptional(new Person())).ifPresent(System.out::println);
    }

    String getInsuranceNameByOptional(Person person){
        // return Optional.ofNullable(person).filter(p -> p.getCar() != null && p.getCar().getInsurance() != null).map(p -> p.getCar().getInsurance().getName()).orElse("unKnown");
        return  Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unKnown");
    }

    String getHouseAddressByOptional(Person person){
        return Optional.ofNullable(person)
                .flatMap(Person::getHouse)
                .map(House::getAddress)
                .orElse("unKnown");
    }

    /**
     * 通过Optional方式获取保险的名称
     * @param insurance
     * @return
     */
    String getInsuranceNameByOptional(Insurance insurance){
        return  Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }

}
