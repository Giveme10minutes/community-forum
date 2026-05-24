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

    @PostMapping
    public Result<Void> post(@CurrentUserId Long userId, Long categoryId, String title, String content) {
        Post post;
        post = postService.post(userId, categoryId, title, content);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<Post> getPostById(@PathVariable Long id) {
        Post post;
        post = postService.getPostById(id);
        post.setViewCount(postService.getPostViewCount(post.getId()));
        return Result.success(post);
    }

    @GetMapping
    public Result<Page<Post>> getPostByPage(int pageNum, int pageSize, @RequestParam(required = false) Long categoryId) {
        Page<Post> list;
        list = postService.getPostByPage(pageNum, pageSize, categoryId);
        return Result.success(list);
    }
}

