package com.yyyxl.controller;

import com.yyyxl.domain.ResponseResult;
import com.yyyxl.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类查询
     * @return
     */
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        ResponseResult categoryList = categoryService.getCategoryList();
        return categoryList;
    }


}
