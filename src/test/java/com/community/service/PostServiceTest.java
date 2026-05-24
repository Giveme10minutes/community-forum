package com.community.service;

import com.community.cache.PostViewService;
import com.community.common.Exception.BusinessException;
import com.community.entity.Post;
import com.community.mapper.PostMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostMapper postMapper;
    @Mock
    private PostViewService postViewService;

    @InjectMocks
    private PostService postService;


    @Test
    void testPost() {
        Long userId = 1L;
        Long categoryId = 1L;
        String title = "Spring Boot";
        String content = "SpringBoot Test";
        Post post = new Post();
        post.setUserId(userId);
        post.setCategoryId(categoryId);
        post.setTitle(title);
        post.setContent(content);

        when(postMapper.insert(any(Post.class))).thenAnswer(invocation -> {
            Post p = invocation.getArgument(0);
            p.setId(1L);
            return 1;
        });
        when(postMapper.selectById(1L)).thenAnswer(invocation -> {
            return post;
        });

        Post testPost = postService.post(userId, categoryId, title, content);

        assertEquals(post.getId(), testPost.getId());
    }

    @Test
    void testPost_insertFailed(){
        Long userId = 1L;
        Long categoryId = 1L;
        String title = "Spring Boot";
        String content = "SpringBoot Test";
        Post post = new Post();
        post.setUserId(userId);
        post.setCategoryId(categoryId);
        post.setTitle(title);
        post.setContent(content);

        when(postMapper.insert(any(Post.class))).thenReturn(0);

        assertThrows(BusinessException.class,()->{
            postService.post(userId,categoryId,title,content);
        });
    }

    @Test
    void testIncrement(){
        when(postViewService.increment(any())).thenReturn(1L);
        Long viewCount=postService.getPostViewCount(100L);
        assertEquals(1L, viewCount);


    }


}
