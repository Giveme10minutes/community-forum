# 社区论坛 MVP — 开发计划

## 技术栈
Spring Boot 4.0.6 + MyBatis-Plus 3.5.12 + MySQL 9 + JJWT 0.12.7 + Redis

## MVP 范围
用户注册 → 登录 → 发帖 → 帖子列表/详情 → 评论

---

## 阶段完成情况

| 阶段 | 内容 | 状态 |
|------|------|:----:|
| 0 | 项目搭建（数据库、配置、健康检查） | ✅ |
| 1 | 用户模块（注册 + 登录 + BCrypt） | ✅ |
| 2 | JWT 认证体系（拦截器 + @CurrentUserId） | ✅ |
| 3 | 分类模块（列表接口 + 数据库预置数据） | ✅ |
| 4 | 帖子模块（发帖、分页列表、按分类筛选、详情） | ✅ |
| 5 | 评论模块（发评论、评论列表、更新 comment_count） | ✅ |
| 6 | 全局异常处理 + 参数校验 | ✅ |
| 7 | Redis 浏览量缓存（递增 + 定时同步回 DB） | ✅ |

**MVP 全部完成 🎉**

---

## 包结构

```
com.community
├── controller/     — REST 接口
├── service/        — 业务逻辑
├── mapper/         — MyBatis-Plus BaseMapper
├── entity/         — 数据库实体
├── dto/            — 请求 DTO
├── vo/             — 响应 VO
├── common/
│   ├── result/     — Result<T> 统一返回
│   ├── exception/  — 全局异常处理
│   └── constant/   — 常量
├── cache/          — Redis 缓存层
├── config/         — JwtInterceptor, WebMvcConfig
├── utils/          — JwtUtil
└── annotation/     — @CurrentUserId
```
