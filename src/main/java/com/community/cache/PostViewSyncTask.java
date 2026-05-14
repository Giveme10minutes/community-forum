package com.community.cache;

import com.community.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class PostViewSyncTask {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private PostMapper postMapper;

    @Scheduled(fixedRate = 300_300)
    public void syncViewCountToDb() {
        Set<String> keys = redisTemplate.keys("post:view:*");
        if (keys == null || keys.isEmpty()) {
            return;
        }

        for (String key : keys) {
            try {
                Long postId = Long.valueOf(key.substring("post:view:".length()));
                String value = redisTemplate.opsForValue().get(key);
                if (value == null) return;

                Long viewCount = Long.valueOf(value);
                postMapper.addViewCount(postId, viewCount);
                redisTemplate.delete(key);
                log.info("同步浏览量成功, postId: {}, viewCount: {}", postId, viewCount);
            }catch (Exception e){
                log.error("同步浏览量失败, key: {}", key, e);
            }
        }
    }
}
