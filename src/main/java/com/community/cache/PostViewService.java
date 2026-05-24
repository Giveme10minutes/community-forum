package com.community.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostViewService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Long increment(Long postId) {
        String key = "post:view:" + postId;
        return redisTemplate.opsForValue().increment(key);
    }

    public Long incrementViewCount(Long postId) {
        String key = "post:view:" + postId;
        String  string = redisTemplate.opsForValue().get(key);
        if (string == null) {
            return 0L;
        }

        return Long.valueOf(string);
    }
}
