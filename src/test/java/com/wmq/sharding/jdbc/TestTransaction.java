package com.wmq.sharding.jdbc;


import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.transaction.OrderTransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ShardingJdbcLearnApplication.class)
@RunWith(SpringRunner.class)
public class TestTransaction {

    @Autowired
    private OrderTransactionService orderTransactionService;

    @Test
    public void testNotSupported(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testNotSupported");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testNotSupported");
        orderTransactionService.insertOrderNotSupported(orderEntity);
    }


    @Test
    public void testMandatory(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testMandatory");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testMandatory");
        orderTransactionService.insertOrderWithoutTransaction(orderEntity);
    }

    @Test
    public void testNever(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testNever");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testNever");
        orderTransactionService.insertOrderNever(orderEntity);
    }

    @Test
    public void testRequiresNew(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testRequiresNew");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testRequiresNew");
        orderTransactionService.insertOrderRequiresNew(orderEntity);
    }

    @Test
    public void testNested(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testNested");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testNested");
        orderTransactionService.insertOrderNested(orderEntity);
    }
}
