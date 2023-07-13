package com.yyyxl.controller;

import com.yyyxl.domain.ResponseResult;
import com.yyyxl.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 查询热门文章
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        ResponseResult result = articleService.hotArticleList();
        return result;

    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){

        return articleService.articleList(pageNum,pageSize,categoryId);
    }

}
