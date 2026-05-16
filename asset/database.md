# 用户表

1. user_id
2. username
3. password
4. create_time
5. update_time
6. phone(非必要)

# 帖子

1. id
2. user_id
3. category_id
4. title
5. content
6. comment_count
7. create_time
8. update_time

# 分类category
1. id
2. name

# 评论

1. id
2. user_id
3. post_id
4. content
5. create_time

# 表

CREATE TABLE user(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password CHAR(60) NOT NULL COMMENT '密码(BCrypt加密)',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_username (username),
    INDEX(id, username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE post(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '创建帖子的用户',
    category_id BIGINT UNSIGNED COMMENT '分区',
    title VARCHAR(200) NOT NULL COMMENT '帖子标题',
    content TEXT NOT NULL COMMENT '帖子内容',
    comment_count BIGINT DEFAULT 0 COMMENT '帖子的评论数量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '帖子更新时间',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='帖子表';

CREATE TABLE category(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(20) NOT NULL COMMENT '分类名字',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

CREATE TABLE comment(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '评论用户的ID',
    post_id BIGINT UNSIGNED NOT NULL COMMENT '评论帖子的ID',
    content TEXT NOT NULL COMMENT '评论',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    PRIMARY KEY (id),
    CONSTRAINT fk_community_forum_user_id
    FOREIGN KEY (user_id)
    REFERENCES user(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE, 
    CONSTRAINT fk_community_forum_post_id
    FOREIGN KEY (post_id)
    REFERENCES post(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';


