package com.wmq.sharding.jdbc.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderEntity {

    private Long id;
    private String orderId;
    private String userId;
    private String userName;
    private String nameDecrypt;
    private String nameEncrypt;
    private String nameAssisted;
    private String name;
}