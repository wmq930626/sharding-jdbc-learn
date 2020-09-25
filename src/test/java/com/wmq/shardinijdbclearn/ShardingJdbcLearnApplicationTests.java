package com.wmq.shardinijdbclearn;

import com.wmq.shardinijdbclearn.entity.OrderEntity;
import com.wmq.shardinijdbclearn.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = ShardingJdbcLearnApplication.class)
@RunWith(SpringRunner.class)
class ShardingJdbcLearnApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void testInsert() {
        for (int i = 0; i < 50; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserName("Tom" + i);
            orderEntity.setOrderId("E167424672" + i);
            orderEntity.setUserId("WMQ186009" + i);
            orderMapper.insert(orderEntity);
        }
    }

    @Test
    void testSelectAll(){
        List<OrderEntity> orderEntities = orderMapper.selectAll();
        orderEntities.forEach(t-> System.out.println(t));
    }

}
