package com.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("post")
public class Post {
    private Long id;
    private Long userId;
    private  Long categoryId;
    private String title;
    private String content;
    private Long commentCount;
    private Date createTime;
    private Date updateTime;
}
