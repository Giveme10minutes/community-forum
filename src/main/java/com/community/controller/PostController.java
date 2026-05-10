package com.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.annotation.CurrentUserId;
import com.community.common.Result;
import com.community.entity.Post;
import com.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public Result<Void> post(@CurrentUserId Long userId, Long categoryId, String title, String content) {
        Post post;
        try {
            post = postService.post(userId, categoryId, title, content);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<Post> getPostById(@PathVariable Long id) {
        Post post;
        try {
            post=postService.getPostById(id);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(post);
    }

    @GetMapping("/getPostByPage")
    public Result<Page<Post>> getPostByPage(int pageNum, int pageSize, @RequestParam(required = false) Long categoryId) {
        Page<Post> list;
        try {
            list = postService.getPostByPage(pageNum, pageSize, categoryId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(list);
    }
}

