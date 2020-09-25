package com.wmq.shardinijdbclearn.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderEntity {

    private Long id;
    private String orderId;
    private String userId;
    private String userName;
}