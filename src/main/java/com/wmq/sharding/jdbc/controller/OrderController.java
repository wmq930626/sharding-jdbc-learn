package com.wmq.sharding.jdbc.controller;


import com.wmq.sharding.jdbc.service.OrderService;
import com.wmq.sharding.jdbc.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kane on 2018/1/17.
 */
@Service
@RestController
public class OrderController {
 
    @Autowired
    private OrderService orderService;
 
    //测试
    @RequestMapping(value="submitOrder")
    public String submitOrder(@RequestParam(value = "userId") String userId,
                              @RequestParam(value = "orderId") String orderId,
                              @RequestParam(value = "userName") String userName) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setOrderId(orderId);
        orderEntity.setUserName(userName);
        orderService.insert(orderEntity);
        return "test";
    }
}