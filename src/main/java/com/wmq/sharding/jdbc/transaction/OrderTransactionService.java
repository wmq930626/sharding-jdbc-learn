package com.wmq.sharding.jdbc.transaction;

import com.wmq.sharding.jdbc.entity.DictEntity;
import com.wmq.sharding.jdbc.entity.OrderEntity;
import com.wmq.sharding.jdbc.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderTransactionService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DictTransactionService dictTransactionService;

    /**
     * 外层有事务（REQUIRED）
     *
     * 内层采用NOT_SUPPORTED将当前事务挂起
     *
     * 当出现异常后只会回滚外层数据，内层由于事务挂起，直接提交不会回滚
     * @param orderEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertOrderNotSupported(OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("NOT_SUPPORTED ");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("NOT_SUPPORTED ");
        dictTransactionService.insertDicNotSupported(dictEntity);
        if ("testRequired".equals(orderEntity.getUserName()))
            throw new RuntimeException("==============");
        return insert;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertOrderRequired(OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("REQUIRED");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("REQUIRED ");
        try {
            dictTransactionService.insertDictRequired(dictEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }

    /**
     * 外层有事务，内层使用NEVER事务隔离级别
     * 抛出异常
     * org.springframework.transaction.IllegalTransactionStateException:
     * Existing transaction found for transaction marked with propagation 'never'
     * @param orderEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertOrderNever(OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("NEVER");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("NEVER");
        dictTransactionService.insertDicNever(dictEntity);
        return insert;
    }

    /**
     * 外层没有事务，内层采用MANDATORY，此时会抛出异常
     * org.springframework.transaction.IllegalTransactionStateException:
     * No existing transaction found for transaction marked with propagation 'mandatory'
     * @param orderEntity
     * @return
     */
    public int insertOrderWithoutTransaction(OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("MANDATORY");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("MANDATORY");
        dictTransactionService.insertDicMandatory(dictEntity);
        return insert;
    }

    /**
     *
     * 不会管外层事务，直接开启一个新的事务，测试会发现，内外同事更新同一个数据，会出现数据库死锁
     * 这就是因为外层事务未提交，内层有开启了一个新的事务引起的
     *
     * Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException:
     * Lock wait timeout exceeded; try restarting transaction
     * @param orderEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertOrderRequiresNew (OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("REQUIRES_NEW");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("REQUIRES_NEW");
        OrderEntity update = new OrderEntity();
        update.setId(525261663511773185L);
        update.setUserName("testRequiresNew001");
        orderMapper.updateOrder(update);
        dictTransactionService.insertRequiresNew(dictEntity);
        return insert;
    }

    /**
     * 嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。外层事务失败时，会回滚内层事务所做的动作。
     * 而内层事务操作失败并不会引起外层事务的回滚。
     * @param orderEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int insertOrderNested(OrderEntity orderEntity){
        int insert = orderMapper.insert(orderEntity);
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictType("NESTED");
        dictEntity.setDictCode(UUID.randomUUID().toString().replace("-",""));
        dictEntity.setDictName("NESTED");
        try {
            dictTransactionService.insertNested(dictEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }
}
