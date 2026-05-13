package com.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class Comment {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;
    private Date createTime;
}
