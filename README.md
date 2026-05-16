# Community Forum

## The project description
a technology community/forum

## Technology stack
1. Java 25
2. Spring Boot 4.0.6
3. Mybatis-Plus 3.5.14 
4. Lombok 
5. MySQL 9.6
6. Redis 8.6
7. JWT 0.12.7

## Function list
1. User login and register. JWT-based authentication intercepts unauthenticated request 
2. User post, page view post
3. Category post
4. Comment post
5. Auto-increment view count using Redis cache

## Quick start
1. Make sure MySQL and Redis services are already running
2. You need  to input SQL file in sql/community-forum.sql
3. Config parameter in the application.yml
   Must be configured
   1. DATABASE_USER and DATABASE_PASSWORD of datasource
   2. REDIS_PASSWORD of redis of data
   3. JWT_SECRET -- JWT key signature
4. run "mvn spring-boot:run" in direction of datasource
5. Visit http://localhost:8080/swagger-ui/index.html to get API document

## Project structure
```
com.community
├── annotation
│   └── CurrentUserId.java          # Current user annotation
├── cache
│   ├── PostViewService.java         # View count cache using Redis
│   └── PostViewSyncTask.java        # timing synchronization view count to DB
├── common
│   ├── Exception
│   │   ├── BusinessException.java   # custom exception 
│   │   └── GlobalExceptionHandler.java
│   └── Result.java                  # unified respond result
├── config
│   ├── JwtInterceptor.java          # JWT login interceptor
│   ├── MyBatisPlusConfig.java       # MyBatis-Plus configuration
│   ├── UserIdHandlerArgumentResolver.java
│   └── WebConfigurer.java           # Web configure（register interceptor）
├── controller                       # REST interface layer
│   ├── CategoryController.java
│   ├── CommentController.java
│   ├── PostController.java
│   ├── UserController.java
├── dto
│   └── RegisterRequest.java         # Login/Register DTO
├── entity                           # database entity
│   ├── Category.java
│   ├── Comment.java
│   ├── Post.java
│   └── User.java
├── mapper                           # MyBatis-Plus Mapper
│   ├── CategoryMapper.java
│   ├── CommentMapper.java
│   ├── PostMapper.java
│   └── UserMapper.java
├── service                          # service layer
│   ├── CategoryService.java
│   ├── CommentService.java
│   ├── PostService.java
│   └── UserService.java
└── utils
└── JwtUtil.java                 # JWT Util
```

## API document
http://localhost:8080/swagger-ui/index.html