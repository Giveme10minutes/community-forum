package com.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.config.UserIdHandlerArgumentResolver;
import com.community.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;
    private MockMvc mockMvc;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setCustomArgumentResolvers(new UserIdHandlerArgumentResolver())
                .build();
    }

    @Test
    void testPost() throws Exception {
        when(postService.post(any(), any(), any(), any())).thenReturn(null);

        mockMvc.perform(post("/post")
                        .requestAttr("userId", 1L)
                        .param("categoryId", "1")
                        .param("title", "标题")
                        .param("content", "内容"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPostByPage() throws Exception {
        when(postService.getPostByPage(anyInt(), anyInt(), any())).thenReturn(new Page<>(0, 10));

        mockMvc.perform(get("/post")
                        .param("pageNum", "1")
                        .param("pageSize", "10")
                        .param("categoryId", "1"))
                .andExpect(status().isOk());


    }


}
