package com.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    @Update("UPDATE post SET view_count = view_count + #{viewCount} WHERE id = #{postId}")
    void addViewCount(@Param("postId") Long postId, @Param("viewCount") Long viewCount);
}
