package com.community;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.community.mapper")
public class CommunityForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityForumApplication.class, args);
    }
}
