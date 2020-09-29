package com.wmq.sharding.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wmq.sharding.jdbc.mapper")
public class ShardingJdbcLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcLearnApplication.class, args);
    }

}
