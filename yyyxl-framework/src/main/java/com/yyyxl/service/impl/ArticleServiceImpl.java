package com.yyyxl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyyxl.domain.entity.Article;
import com.yyyxl.mapper.ArticleMapper;
import com.yyyxl.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
