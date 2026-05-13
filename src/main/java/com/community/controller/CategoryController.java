package com.community.controller;

import com.community.common.Result;
import com.community.entity.Category;
import com.community.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<List<Category>> listAll(){
        List<Category> list;
        try {
            list=categoryService.listCategory();
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
        return Result.success(list);
    }
}
