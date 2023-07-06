package com.yyyxl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyyxl.constans.SystemConstants;
import com.yyyxl.domain.ResponseResult;
import com.yyyxl.domain.entity.Article;
import com.yyyxl.domain.vo.HotArticleVo;
import com.yyyxl.mapper.ArticleMapper;
import com.yyyxl.service.ArticleService;
import com.yyyxl.utils.BeanCopyUtils;
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

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多查10条
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        // bean拷贝
        /*List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo vo = new HotArticleVo();
            // bean 拷贝字段名字和字段类型要相同
            BeanUtils.copyProperties(article,vo);
            articleVos.add(vo);
        }*/

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);

    }
}
