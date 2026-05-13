package com.community.controller;

import com.community.annotation.CurrentUserId;
import com.community.common.Result;
import com.community.entity.Comment;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Result<Void> addComment(@CurrentUserId Long userId, Long postId, String content) {
        try {
            commentService.addComment(userId, postId, content);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(null);
    }

    @GetMapping
    public Result<List<Comment>> getCommentByPostId(Long postId) {
        List<Comment> list;
        try {
            list = commentService.getCommentByPostId(postId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(list);
    }
}
