package com.xx.bootblog.service;

import com.xx.bootblog.domain.dto.Article;
import com.xx.bootblog.domain.dto.PageInfo;

public interface ArticleService {
    PageInfo<Article> getArticleByPage(Integer pageSize, Integer pageNum);

    void delArticle(Integer id);
}
