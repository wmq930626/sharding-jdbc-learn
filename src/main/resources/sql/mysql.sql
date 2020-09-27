DROP DATABASE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '顺序编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=516249862656753665 DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP DATABASE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                             `order_id` varchar(32) DEFAULT NULL COMMENT '顺序编号',
                             `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
                             `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=516249862656753665 DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP DATABASE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `dict_type` varchar(32) DEFAULT NULL COMMENT '字典类型',
                          `dict_code` varchar(32) DEFAULT NULL COMMENT '用户编号',
                          `dict_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础字典表';