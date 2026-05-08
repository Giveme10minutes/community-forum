package com.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    Long id;
    String userName;
    String password;
    Date createTime;
    Date updateTime;
}
