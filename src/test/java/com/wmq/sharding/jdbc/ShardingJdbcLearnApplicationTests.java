package com.wmq.sharding.jdbc;

import com.wmq.sharding.jdbc.entity.DictEntity;
import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.mapper.DictMapper;
import com.wmq.sharding.jdbc.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = ShardingJdbcLearnApplication.class)
@RunWith(SpringRunner.class)
class ShardingJdbcLearnApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DictMapper dictMapper;

    @Test
    void testInsert() {
        for (int i = 0; i < 300; i++) {
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

    @Test
    void testSelectByParam(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("E16742467230");
        List<OrderEntity> orderEntities = orderMapper.selectByParam(orderEntity);
        orderEntities.forEach(t-> System.out.println(t));
    }

    @Test
    void testSelectByIdList(){
        List<Long> orderIdList = Arrays.asList(516365907660898304L,
                516365907702841344L,
                516365907753172992L,
                516365907795116032L,
                516365907832864768L,
                516365907874807808L,
                516365907916750848L,
                516365907199524865L,
                516365907681869825L,
                516365907732201473L,
                516365907774144513L,
                516365907811893249L,
                516365907853836289L,
                516365907895779329L,
                516365907933528065L);
        List<OrderEntity> orderEntities = orderMapper.selectByOrderIdList(orderIdList);
        orderEntities.forEach(t-> System.err.println(t));
    }

    @Test
    void testSelectById(){
        OrderEntity orderEntity = orderMapper.selectOrderById(516722582712483840L);
        System.out.println(orderEntity);
    }

    @Test
    void testInsertDict(){
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("order_status");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("待收货");
        dictMapper.insert(dictEntity);
    }

    @Test
    void testSelectDict(){
        List<DictEntity> dictEntities = dictMapper.selectAll();
        dictEntities.forEach(t->{
            System.err.println(t.toString());
        });
    }

    @Test
    void testUpdateDict(){
        DictEntity dictEntity = new DictEntity();
        dictEntity.setId(2L);
        dictEntity.setDictName("待发货");
        dictMapper.updateDictById(dictEntity);
    }
}
