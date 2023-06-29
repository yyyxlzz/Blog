package com.yyyxl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyyxl.domain.ResponseResult;
import com.yyyxl.domain.entity.Article;
import com.yyyxl.mapper.ArticleMapper;
import com.yyyxl.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

        /**
         * 查询热门文章
         * @return
         */
    @Override
    public ResponseResult hotArticleList() {
        ResponseResult result = new ResponseResult();
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus,0);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多查10条
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);

    }
}
