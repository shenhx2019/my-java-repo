package com.java8.datetimedemo;

import com.java8.optional.entity.Person;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootTest
public class DateTimeDemoTest {

    @Test
    void testNewDateTimeApiOfJava8(){
        LocalDate d = LocalDate.of(2019,11,19);
        assert 2019 == d.getYear();
        assert 11 == d.getMonthValue();
        assert 19 == d.getDayOfMonth();
        System.out.println(d.getDayOfWeek());
    }

    @Test
    void testInstant() throws InterruptedException {
        Instant begin = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        System.out.println(Duration.between(begin,end).getSeconds()+"秒");
    }

    @Test
    void testPeriod(){
        LocalDate ld1 = LocalDate.of(2019,11,12);
        LocalDate ld2 = LocalDate.of(2019,11,19);
        Period p = Period.between(ld1,ld2);
        System.out.println(p.getDays());
    }

    @Test
    void testFormat(){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINESE);
        LocalDate d = LocalDate.now();
        d.format(f);
    }
}
