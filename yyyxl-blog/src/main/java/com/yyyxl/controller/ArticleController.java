package com.yyyxl.controller;

import com.yyyxl.domain.ResponseResult;
import com.yyyxl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotarticlelist")
    public ResponseResult hotArticleList(){

        ResponseResult result = articleService.hotArticleList();
        return result;



    }

}
