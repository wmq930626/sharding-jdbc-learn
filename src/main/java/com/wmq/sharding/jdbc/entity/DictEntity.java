package com.wmq.sharding.jdbc.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DictEntity {
    private Long id;
    private String dictType;
    private String dictCode;
    private String dictName;
}
