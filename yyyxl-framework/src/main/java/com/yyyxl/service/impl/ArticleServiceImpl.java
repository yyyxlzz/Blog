package com.yyyxl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyyxl.constans.SystemConstants;
import com.yyyxl.domain.ResponseResult;
import com.yyyxl.domain.entity.Article;
import com.yyyxl.domain.vo.ArticleListVo;
import com.yyyxl.domain.vo.HotArticleVo;
import com.yyyxl.domain.vo.PageVo;
import com.yyyxl.mapper.ArticleMapper;
import com.yyyxl.service.ArticleService;
import com.yyyxl.service.CategoryService;
import com.yyyxl.utils.BeanCopyUtils;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询热门文章
     *
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
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

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

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        // 如果 有categoryId 就要 查询时要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 状态是正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.STATUS_NORMAL);
        // 对isTop经行降序
        queryWrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page(pageNum,pageSize);
        page(page, queryWrapper);

        // 查询分类名称
        List<Article> articles = page.getRecords();
        for (Article article : articles) {
            Category byId = categoryService.getById(article.getId());
            article.setCategoryName(byId.getName());
        }

        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);



        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

}
