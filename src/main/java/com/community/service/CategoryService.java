package com.community.service;

import com.community.entity.Category;
import com.community.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> listCategory(){
        List<Category> list;
        list=categoryMapper.selectList(null);

        if(list==null){
            throw new RuntimeException("查询失败，请重试");
        }

        return list;
    }
}
