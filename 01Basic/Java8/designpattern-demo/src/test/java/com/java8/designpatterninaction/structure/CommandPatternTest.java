package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.behaviour.command.Broker;
import com.java8.designpatterninaction.behaviour.command.Stock;
import com.java8.designpatterninaction.behaviour.command.impl.BuyStock;
import com.java8.designpatterninaction.behaviour.command.impl.SellStock;
import com.java8.designpatterninaction.behaviour.command.param.StockRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandPatternTest {

    @Test
    void test(){
        Stock stock = new Stock("1001");
        stock.setQuantity(100);
        StockRequest request = new StockRequest();
        request.setNo("1001");
//        request.setQuantity(1000);
        // 声明接收者
        Broker broker = new Broker();
        broker.takeOrder(new BuyStock(stock), request);
        // broker.placeOrders();
        StockRequest request1 = new StockRequest();
//        request.setNo("1001");
//        request.setQuantity(100);
        broker.takeOrder(new SellStock(stock), request);
        broker.placeOrders();
    }
}
