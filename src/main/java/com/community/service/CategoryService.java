package com.community.service;

import com.community.common.Exception.BusinessException;
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
            throw new BusinessException("查询失败，请重试");
        }

        return list;
    }
}
