package com.wmq.shardinijdbclearn.controller;


import com.wmq.shardinijdbclearn.entity.OrderEntity;
import com.wmq.shardinijdbclearn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kane on 2018/1/17.
 */
@Service
@RestController
public class OrderController {
 
    @Autowired
    private OrderService orderService;
 
 /*   @RequestMapping("/getUsers")
    public List<OrderEntity> getUsers() {
        List<OrderEntity> orders=orderService.getOrders();
        return orders;
    }*/
 
    //测试
    @RequestMapping(value="update1")
    public String updateTransactional(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "orderId") String orderId,
                                    @RequestParam(value = "userName") String userName
                                    ) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setOrderId(orderId);
        orderEntity.setUserName(userName);
        orderService.insert(orderEntity);
        return "test";
    }
}