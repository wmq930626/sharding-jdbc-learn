package com.wmq.sharding.jdbc.mapper;


import com.wmq.sharding.jdbc.entity.OrderEntity;
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

    @Select("select * from t_order where id = #{id}")
    OrderEntity selectOrderById(Long id);

    @Select("<script>"+
                "select * from t_order where id in" +
                "<foreach  collection = 'orderIdList'  item = 'id'  open = '('  separator=',' close=')'>" +
                    "#{id}" +
                "</foreach>" +
            "</script>")
    List<OrderEntity> selectByOrderIdList(@Param("orderIdList") List<Long> idList);
}
 