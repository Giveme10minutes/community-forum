⏺ 💬 社区论坛 · 开发进度

阶段 0 — 项目初始化
⚪ 创建 Spring Boot 项目
⚪ 配置 application.yml（MySQL + MyBatis）
⚪ 执行建表 SQL（4 张表）
⚪ 跑通测试接口

阶段 1 — 用户模块
⚪ User 实体 + Mapper + XML
⚪ UserService + 实现类
⚪ 注册接口（BCrypt）
⚪ 登录接口（返回 JWT）

阶段 2 — 认证体系
⚪ JwtUtil（生成/解析）
⚪ JwtInterceptor
⚪ WebMvcConfig 注册拦截器
⚪ @CurrentUserId 注解 + 参数解析器

阶段 3 — 分类模块
⚪ Category 实体 + Mapper + Service
⚪ 分类列表接口
⚪ 数据库预置分类数据

阶段 4 — 帖子模块（核心）
⚪ Post 实体 + Mapper + XML
⚪ PostService + 实现类
⚪ 发帖接口
⚪ 帖子列表（分页 + 按分类筛选）
⚪ 帖子详情

阶段 5 — 评论模块
⚪ Comment 实体 + Mapper + XML
⚪ CommentService + 实现类
⚪ 发表评论接口
⚪ 评论列表接口

阶段 6 — 统一能力补充
⚪ Result 统一返回
⚪ BusinessException + GlobalExceptionHandler
⚪ @Valid 参数校验

阶段 7 — MVP 优化
⚪ 浏览量 +1

阶段8 — 屏蔽词检测
⚪ 屏蔽敏感词
  ---
最终验证
⚪ 注册 → 登录 → 发帖 → 看列表 → 看详情 → 评论

## 明确不做

- 点赞 / 收藏
- 搜索
- 排行榜
- 标签系统
- 后台管理

---

## 完成标准

完整走通以下流程即算 MVP 完成：

> **注册 → 登录 → 发帖 → 看列表 → 看详情 → 评论**

可以写进简历，可以用于面试讲项目。


# 💬 社区论坛

> 第三个 Spring Boot 项目，技术社区/论坛方向
>
- **时间**：2026-05-06 至今
- **源码**：[communtiy-forum](/Users/chenyu/Documents/Java/Project/community-forum)
- **状态**：⏳ 开发中
- **技术栈**：Spring Boot 4.0.6 + MyBatis + MySQL 9 + JWT + Redis

---

## 里程碑

### MVP — 基础社区功能
用户注册登录、发帖看帖、评论互动、分类筛选，走通完整业务流程。

### v2 — 社区互动增强
点赞、收藏、热门排行、搜索功能。

---

## 功能

### 用户模块
| 接口 | 说明 |
|------|------|
| `POST /api/user/register` | 注册（BCrypt 加密密码） |
| `POST /api/user/login` | 登录，返回 JWT |

### 帖子模块
| 接口 | 说明 |
|------|------|
| `POST /api/post` | 发帖（需登录） |
| `GET /api/post/list` | 帖子列表（分页 + 按分类筛选） |
| `GET /api/post/{id}` | 帖子详情 |

### 评论模块
| 接口 | 说明 |
|------|------|
| `POST /api/comment` | 发表评论（需登录） |
| `GET /api/comment/list?postId=x` | 查看帖子评论列表 |

### 分类模块
| 接口 | 说明 |
|------|------|
| `GET /api/category/list` | 获取全部分类 |

---

## 技术栈

| 技术              | 用途             |
| --------------- | -------------- |
| Spring Boot 3.4 | 应用框架           |
| MyBatis + XML   | 数据访问层          |
| MySQL 9         | 数据持久化          |
| JWT (jjwt)      | 登录认证           |
| Redis           | 缓存浏览量、热门排行（v2） |
| Maven           | 项目构建           |

---

## 项目结构

```
community-forum/
├── src/main/java/com/forum/
│   ├── ForumApplication.java
│   ├── common/
│   │   ├── result/Result.java
│   │   ├── exception/GlobalExceptionHandler.java
│   │   ├── exception/BusinessException.java
│   │   └── config/WebMvcConfig.java
│   ├── auth/
│   │   ├── JwtUtil.java                — JWT 生成/解析
│   │   ├── JwtInterceptor.java         — 登录校验拦截器
│   │   └── CurrentUserId.java          — 获取当前用户注解
│   ├── user/
│   │   ├── controller/UserController.java
│   │   ├── service/UserService.java
│   │   ├── service/impl/UserServiceImpl.java
│   │   ├── mapper/UserMapper.java
│   │   ├── entity/User.java
│   │   └── dto/LoginRequest.java, RegisterRequest.java, UserVO.java
│   ├── category/
│   │   ├── controller/CategoryController.java
│   │   ├── service/CategoryService.java
│   │   ├── service/impl/CategoryServiceImpl.java
│   │   ├── mapper/CategoryMapper.java
│   │   ├── entity/Category.java
│   │   └── dto/CategoryVO.java
│   ├── post/
│   │   ├── controller/PostController.java
│   │   ├── service/PostService.java
│   │   ├── service/impl/PostServiceImpl.java
│   │   ├── mapper/PostMapper.java
│   │   ├── entity/Post.java
│   │   └── dto/PostCreateRequest.java, PostListVO.java, PostDetailVO.java
│   └── comment/
│       ├── controller/CommentController.java
│       ├── service/CommentService.java
│       ├── service/impl/CommentServiceImpl.java
│       ├── mapper/CommentMapper.java
│       ├── entity/Comment.java
│       └── dto/CommentRequest.java, CommentVO.java
├── src/main/resources/
│   ├── application.yml
│   └── mapper/
│       ├── UserMapper.xml
│       ├── CategoryMapper.xml
│       ├── PostMapper.xml
│       └── CommentMapper.xml
└── pom.xml
```

---

## 数据库设计

### 表关系

```
user ──1:N──> post ──N:1──> category
                  │
                  └──1:N──> comment ──N:1──> user
```

### user（用户表）

```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,        -- BCrypt 加密
    email VARCHAR(100) UNIQUE,
    avatar VARCHAR(255) DEFAULT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### category（分类表）

```sql
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200) DEFAULT NULL,
    sort INT DEFAULT 0                     -- 排序权重
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO category (name, description, sort) VALUES
('技术分享', '编程、框架、工具等', 1),
('问答求助', '遇到问题寻求帮助', 2),
('资源推荐', '好书、好课、好工具', 3);
```

### post（帖子表）

```sql
CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    view_count INT DEFAULT 0,
    comment_count INT DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_user_id (user_id),
    INDEX idx_category_id (category_id),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### comment（评论表）

```sql
CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    content VARCHAR(500) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_post_id (post_id),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
