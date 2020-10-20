package com.wmq.sharding.jdbc.transaction;

import com.wmq.sharding.jdbc.entity.DictEntity;
import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.mapper.DictMapper;
import com.wmq.sharding.jdbc.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictTransactionService {
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertDictRequired(DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        if (dictEntity.getDictType().equals("REQUIRED"))
            throw new RuntimeException("==========");
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.MANDATORY)
    public int insertDicMandatory(DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public int insertDicNotSupported(DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NEVER)
    public int insertDicNever(DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public int insertRequiresNew (DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(525261663511773185L);
        orderEntity.setUserName("testRequiresNew002");
        orderMapper.updateOrder(orderEntity);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public int insertNested(DictEntity dictEntity){
        int insert = dictMapper.insert(dictEntity);
        if (dictEntity.getDictType().equals("NESTED"))
            throw new RuntimeException("===========");
        return insert;
    }
}
