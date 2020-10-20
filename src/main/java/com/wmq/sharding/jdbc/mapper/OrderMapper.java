package com.wmq.sharding.jdbc.mapper;


import com.wmq.sharding.jdbc.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into t_order(order_id,user_id,user_name,name_encrypt) " +
            "values(#{orderId},#{userId},#{userName},#{name})")
    int insert(OrderEntity user);

    @Select("select * from t_order order by user_name asc")
    List<OrderEntity> selectAll();

    @Select("select * from t_order where order_id = #{orderId}")
    List<OrderEntity> selectByParam(OrderEntity orderEntity);

    @Update("update t_order set user_name = #{userName} where id = #{id}")
    int updateOrder(OrderEntity orderEntity);

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
 