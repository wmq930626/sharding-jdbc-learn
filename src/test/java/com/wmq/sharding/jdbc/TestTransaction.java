package com.wmq.sharding.jdbc;


import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.transaction.OrderTransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * REQUIRED 有事务就支持当前事务和外层事务处在同一个事务中,没有事务就会创建事务
 * REQUIRED_NEW 有没有事务都会创建一个事务，和外层事务处在两个事务中
 * MANDATORY 强制使用事务，没有事务就会抛出异常
 * NEVER 强制不使用事务，有事务就会抛出异常
 * NOT_SUPPORTED 不支持事务，有事务会把外层事务挂起，外层事务回滚，不会影响内层事务提交
 * SUPPORTED 支持外层事务，如果没有事务就会以非事务的方式运行
 * NESTED 嵌套事务，和外层事务同步提交回滚至save_point,外层事务回滚内层事务会跟着一起回滚,内层事务回滚不会引起外层事务回滚
 *
 */
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
    public void testRequired(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserName("testRequired");
        orderEntity.setOrderId("E167424672Required");
        orderEntity.setUserId("WMQ186009Required");
        orderEntity.setName("testRequired");
        orderTransactionService.insertOrderRequired(orderEntity);
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
