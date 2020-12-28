package com.xx.bootblog.service;

import com.xx.bootblog.domain.Article;

import java.text.ParseException;
import java.util.List;

public interface ArticleService {

    public List<Article> simpleArticleDetail();

    List<Article> articleListByPid(Integer pid);

    void addArticle(Article article);

    Article getArticle(Integer aid);

    Long getPages(Integer pid);

    List<Article> articlePageListByPid(Integer pid, Integer pagenum);

    void updatePraise(Integer aid);

    void updatePageview(Integer aid);

    Article getPreArticle(Integer aid, Integer pid);

    Article getSufArticle(Integer aid, Integer pid);

    List<Article> searchByContent(String keyWord);

    List<Article> searchByTitle(String keyWord);

    Long getTotal();

    List<Article> getArticleByPid(Integer pid);
}
