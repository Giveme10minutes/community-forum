# Community Forum

## 项目简介
一个技术社区/论坛

## 技术栈
1. Java 25
2. Spring Boot 4.0.6
3. Mybatis-Plus 3.5.14 
4. Lombok 
5. MySQL 9.6
6. Redis 8.6
7. JWT 0.12.7

## 功能清单
1. 用户的登录，注册。使用了JWT进行拦截未登录用户
2. 用户发帖，分页浏览帖子
3. 帖子分类
4. 用户评论帖子
5. 浏览帖子时，使用Redis自动增加浏览量

## 快速启动
1. 确保 MySQL 和 Redis 已启动
2. SQL文件位于sql/community-forum.sql
3. 配置application.yml里面的参数
    必须要设置的配置
   1. datasource的DATABASE_USER和DATABASE_PASSWORD
   2. data下的redis的REDIS_PASSWORD
   3. JWT_SECRET -- JWT密钥签名
4. 文件目录下运行 mvn spring-boot:run
5. 访问 http://localhost:8080/swagger-ui/index.html 查看 API 文档

## 项目结构
com.community
├── annotation
│   └── CurrentUserId.java          # 当前用户注解
├── cache
│   ├── PostViewService.java         # Redis 浏览量缓存
│   └── PostViewSyncTask.java        # 定时同步浏览量到 DB
├── common
│   ├── Exception
│   │   ├── BusinessException.java   # 自定义业务异常
│   │   └── GlobalExceptionHandler.java
│   └── Result.java                  # 统一返回格式
├── config
│   ├── JwtInterceptor.java          # JWT 登录拦截器
│   ├── MyBatisPlusConfig.java       # MyBatis-Plus 配置
│   ├── UserIdHandlerArgumentResolver.java
│   └── WebConfigurer.java           # Web 配置（拦截器注册）
├── controller                       # REST 接口层
│   ├── CategoryController.java
│   ├── CommentController.java
│   ├── PostController.java
│   ├── UserController.java
├── dto
│   └── RegisterRequest.java         # 注册/登录请求 DTO
├── entity                           # 数据库实体
│   ├── Category.java
│   ├── Comment.java
│   ├── Post.java
│   └── User.java
├── mapper                           # MyBatis-Plus Mapper
│   ├── CategoryMapper.java
│   ├── CommentMapper.java
│   ├── PostMapper.java
│   └── UserMapper.java
├── service                          # 业务逻辑层
│   ├── CategoryService.java
│   ├── CommentService.java
│   ├── PostService.java
│   └── UserService.java
└── utils
└── JwtUtil.java                 # JWT 工具类

## API文档
http://localhost:8080/swagger-ui/index.html