package com.wmq.sharding.jdbc.mapper;


import com.wmq.sharding.jdbc.entity.DictEntity;
import com.wmq.sharding.jdbc.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictMapper {
 
    /*
    List<OrderEntity> getAll();
 
    void update(OrderEntity user);
    */
    @Insert("insert into t_dict(dict_type,dict_code,dict_name) " +
            "values(#{dictType},#{dictCode},#{dictName})")
    int insert(DictEntity dictEntity);

    @Select("select * from t_dict")
    List<DictEntity> selectAll();

    @Update({ "update t_dict set dict_name = #{dictName} where id = #{id}" })
    int updateDictById(DictEntity dictEntity);


}
 