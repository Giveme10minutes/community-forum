# CLAUDE.md

此文件为 Claude Code (claude.ai/code) 提供本仓库的代码开发指引。

## 项目概述

Java 后端 + Vue 前端社区论坛（MVP）。用户可注册、登录、发帖、分页浏览帖子列表、查看帖子详情和评论。

## 技术栈

### 后端
- Spring Boot 4.0.6
- MyBatis-Plus 3.5.12（Spring Boot 3.x starter）
- MySQL 9
- JJWT 0.12.7（api + impl + jackson）
- Redis（Spring Data Redis，浏览量缓存）
- Lombok

### 前端
- Vue 3（Vite 构建）
- Vue Router 4
- Axios

## 后端架构

三层架构：Controller → Service → Mapper（MyBatis-Plus BaseMapper）

包结构：
```
com.community
├── controller/     — REST 接口
├── service/        — 业务逻辑
├── mapper/         — MyBatis-Plus BaseMapper 接口
├── entity/         — 数据库实体类
├── dto/            — 请求 DTO
├── vo/             — 响应 VO
├── common/
│   ├── result/     — Result<T> 统一返回
│   ├── exception/  — 全局异常处理
│   └── constant/   — 常量
├── cache/          — Redis 缓存层（浏览量等）
├── config/         — JwtInterceptor, WebMvcConfig, CorsConfig, MyBatis-Plus 配置
├── utils/          — JwtUtil 等工具类
└── annotation/     — @CurrentUserId
```

## 前端结构

```
frontend/
├── src/
│   ├── router/index.js   — 路由配置
│   ├── views/            — 页面组件
│   ├── utils/request.js  — Axios 封装（含 JWT 拦截器）
│   ├── App.vue           — 根组件（导航栏 + router-view）
│   └── main.js           — 入口文件
```

## 开发计划

MVP 各阶段按顺序：
1. **Phase 0**：项目搭建（数据库、配置、健康检查接口）✅
2. **用户模块**：注册 + 登录（BCrypt, JWT）✅
3. **认证体系**：JWT 拦截器 + @CurrentUserId ✅
4. **分类模块**：分类列表（数据库预置数据）✅
5. **帖子模块**（核心）：发帖、分页列表、按分类筛选、帖子详情 ✅
6. **评论模块**：发表评论、评论列表（更新 post.comment_count）✅
7. **通用能力**：Result<T>、全局异常处理、参数校验 ✅
8. **优化**：Redis 浏览量缓存 ✅
9. **前端开发**（进行中）：Vue 3 页面，已实现首页帖子列表、登录页面

## 构建与运行

### 后端
```bash
# 构建
mvn clean compile

# 运行测试
mvn test

# 启动应用
mvn spring-boot:run
# 需要环境变量：DATABASE_USER, DATABASE_PASSWORD, REDIS_PASSWORD
# 或配置 .env 文件由 spring.config.import 加载

# 解析依赖（排查问题时有用）
mvn dependency:resolve
```

### 前端
```bash
cd frontend
npm install
npm run dev
# 默认运行在 http://localhost:5173
```

## 领域约定

- API 基础路径：无（直接 root，如 `/user/login`、`/post`），CORS 已配置允许 `localhost:5173`
- JWT token 通过 `Authorization: Bearer <token>` 头部传递
- 响应统一使用 `Result<T>` 封装，格式：`{ code, message, data }`
- 分页使用 `pageNum` 和 `pageSize` 查询参数
- 需要登录的接口从 JWT 中提取 `user_id`，不从请求体获取
- Redis 配置前缀：`spring.data.redis`（Spring Boot 4.x）
- 浏览量缓存 key 格式：`post:view:{postId}`


你是我的编程教练，不是代码生成器。请严格遵守以下规则指导我完成项目：

1. 不要直接给完整代码，除非我明确要求
2. 每次只引导我完成一个小步骤（例如一个接口或一个方法）
3. 优先用提问的方式引导我思考，而不是直接给答案
4. 如果我写了代码，你要帮我指出问题并解释原因，而不是直接改好
5. 控制信息量，避免一次讲太多
6. 如果我方向错了，要直接指出并纠正
7. 所有讲解以“让我能独立写出来”为目标，而不是帮我完成
8. 如果你发现我在依赖你，请主动减少提示，增加引导。
