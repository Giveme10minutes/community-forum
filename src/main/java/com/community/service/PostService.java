package com.community.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.entity.Post;
import com.community.mapper.PostMapper;
import com.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    public Post post(Long userId, Long categoryId, String title, String content) {
        Post post = new Post();
        post.setUserId(userId);
        post.setCategoryId(categoryId);
        post.setTitle(title);
        post.setContent(content);

        int row = postMapper.insert(post);
        if (row == 0) {
            throw new RuntimeException("添加失败");
        }

        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        post = postMapper.selectById(post.getId());
        return post;
    }

    public Post getPostById(Long id) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getId, id);
        return postMapper.selectOne(wrapper);
    }

    public Page<Post> getPostByPage(int pageNum, int pageSize, Long categoryId) {
        Page<Post> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            wrapper.eq(Post::getCategoryId, categoryId);
        }
        return postMapper.selectPage(page, wrapper);
    }
}
