package com.wmq.shardinijdbclearn.service;


import com.wmq.shardinijdbclearn.entity.OrderEntity;
import com.wmq.shardinijdbclearn.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 
/**
 * Created by Kane on 2018/1/17.
 */
@Slf4j
@Service
public class OrderService {
 
    @Autowired
    private OrderMapper orderMapper;
 
  /*  public List<OrderEntity> getOrders() {
        List<OrderEntity> orders = orderMapper.getAll();
        return orders;
    }
 */
//    @Transactional(value="test1TransactionManager",rollbackFor = Exception.class,timeout=36000)  //说明针对Exception异常也进行回滚，如果不标注，则Spring 默认只有抛出 RuntimeException才会回滚事务
    public void insert(OrderEntity order) {
        try{
            orderMapper.insert(order);
            log.error(String.valueOf(order));
        }catch(Exception e){
            log.error("find exception!");
            throw e;   // 事物方法中，如果使用trycatch捕获异常后，需要将异常抛出，否则事物不回滚。
        }
 
    }
}
 