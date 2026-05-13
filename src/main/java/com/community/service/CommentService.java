package com.community.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.community.entity.Comment;
import com.community.entity.Post;
import com.community.mapper.CommentMapper;
import com.community.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;

    public void addComment(Long userId, Long postId, String content) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setContent(content);

        int rows = commentMapper.insert(comment);
        if (rows == 0) {
            throw new RuntimeException("评论发表失败，请稍后再试");
        }

        LambdaUpdateWrapper<Post> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Post::getId, postId)
                .setSql("comment_count=comment_count+1");
        postMapper.update(wrapper);
    }

    public List<Comment> getCommentByPostId(Long postId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getPostId, postId);
        return commentMapper.selectList(wrapper);
    }
}
