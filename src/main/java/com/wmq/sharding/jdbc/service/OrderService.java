package com.wmq.sharding.jdbc.service;


import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.*;

/**
 * Created by Kane on 2018/1/17.
 */
@Slf4j
@Service
public class OrderService {
 
    @Autowired
    private OrderMapper orderMapper;
    private final ScheduledExecutorService scheduledExecutorService = newSingleThreadScheduledExecutor();
 
    @Transactional(rollbackFor = Exception.class)
    public void insert(OrderEntity order) {
        try{
            OrderEntity orderEntity1 = new OrderEntity();
            orderEntity1.setUserName("Tomxxxxx01");
            orderEntity1.setOrderId("E1674246723");
            orderEntity1.setUserId("WMQ18600911");
            orderMapper.insert(orderEntity1);
            OrderEntity orderEntity2 = new OrderEntity();
            orderEntity2.setUserName("Tomxxxxxx02");
            orderEntity2.setOrderId("E1674246723");
            orderEntity2.setUserId("WMQ18600911");
            orderMapper.insert(orderEntity2);
            log.error(String.valueOf(order));
            throw new RuntimeException();
        }catch(Exception e){
            log.error("find exception!");
            throw e;
        }
 
    }
}
 