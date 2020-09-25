package com.wmq.shardinijdbclearn.mapper;


import com.wmq.shardinijdbclearn.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
 
    /*
    List<OrderEntity> getAll();
 
    void update(OrderEntity user);
    */
    @Insert("insert into t_order(order_id,user_id,user_name) " +
            "values(#{orderId},#{userId},#{userName})")
    int insert(OrderEntity user);

    @Select("select * from t_order")
    List<OrderEntity> selectAll();

    @Select("select * from t_order where order_id = #{orderId}")
    List<OrderEntity> selectByParam(OrderEntity orderEntity);
}
 