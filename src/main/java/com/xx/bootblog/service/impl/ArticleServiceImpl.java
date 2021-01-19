package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.domain.dto.Article;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.po.ArticlePo;
import com.xx.bootblog.mapper.ArticleMapper;
import com.xx.bootblog.mapper.ReviewMapper;
import com.xx.bootblog.service.ArticleService;
import com.xx.bootblog.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public PageInfo<Article> getArticleByPage(Integer pageSize, Integer pageNum) {
        Page<ArticlePo> page = PageHelper.startPage(pageNum, pageSize);
        articleMapper.getAllArticle();
        return PageInfo.<Article>builder().data(DozerUtils.mapList(page.getResult(),Article.class))
                .total(page.getTotal()).pageSize(page.getPageSize()).pageNum(page.getPageNum()).build();
    }

    @Override
    @Transactional
    public void delArticle(Integer id) {
        reviewMapper.deleteByArticleId(id);
        articleMapper.delete(id);
    }

}
