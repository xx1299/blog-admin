package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.domain.Article;
import com.xx.bootblog.mapper.ArticleMapper;
import com.xx.bootblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Article> simpleArticleDetail(){
        PageHelper.startPage(1, 5);
        List<Article> articles = articleMapper.selectArticleByTime();
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");
            article.setContent(s);
        }
        return articles;
    }

    @Override
    public List<Article> articleListByPid(Integer pid) {
        PageHelper.startPage(1, 5);
        List<Article> articles = articleMapper.selectArticleByPid(pid);
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");
            article.setContent(s);
        }
        return articles;
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public Article getArticle(Integer aid) {
        return articleMapper.selectByPrimaryKey(aid);
    }

    @Override
    public Long getPages(Integer pid) {
        Long Total = articleMapper.getTotalByPid(pid);
        return (long)Math.ceil((double)Total/5);
    }

    @Override
    public List<Article> articlePageListByPid(Integer pid, Integer pagenum) {
        PageHelper.startPage(pagenum,5);
        List<Article> articles = articleMapper.selectArticleByPid(pid);
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");
            article.setContent(s);
        }
        return articles;
    }

    @Override
    public void updatePraise(Integer aid) {
        articleMapper.updatePraiseByAid(aid);
    }

    @Override
    public void updatePageview(Integer aid) {
        articleMapper.updatePageview(aid);
    }

    @Override
    public Article getPreArticle(Integer aid, Integer pid) {
        return articleMapper.selectPreArticle(aid,pid);
    }

    @Override
    public Article getSufArticle(Integer aid, Integer pid) {
        return articleMapper.selectSufArticle(aid,pid);
    }

    @Override
    public List<Article> searchByContent(String keyWord) {
        List<Article> articles = articleMapper.selectByContent(keyWord);
        Iterator<Article> iterator = articles.iterator();
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");
            article.setContent(s);
        }
        while (iterator.hasNext()){
            if (iterator.next().getContent().indexOf(keyWord)==-1){
                iterator.remove();
            }
        }
        for (Article article : articles) {
            String s = article.getContent().replaceAll(keyWord, "<mark>"+keyWord+"</mark>");
            article.setContent(s);
        }
        return articles;
    }

    @Override
    public List<Article> searchByTitle(String keyWord) {
        List<Article> articles = articleMapper.selectByTitle(keyWord);
        Iterator<Article> iterator = articles.iterator();
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");
            article.setContent(s);
        }
        while (iterator.hasNext()){
            if (iterator.next().getTitle().indexOf(keyWord)==-1){
                iterator.remove();
            }
        }
        for (Article article : articles) {
            String s = article.getTitle().replaceAll(keyWord, "<mark>"+keyWord+"</mark>");
            article.setTitle(s);
        }
        return articles;
    }

    @Override
    public Long getTotal() {
        return articleMapper.getTotal();
    }

    @Override
    public List<Article> getArticleByPid(Integer pid) {
        List<Article> articles = articleMapper.selectArticleByPid(pid);
        for (Article article : articles) {
            String s = article.getContent().replaceAll("\\<.*?>", "").
                    replaceAll("\\n", "").
                    replaceAll("\\t", "").
                    replaceAll("\\r", "").
                    replaceAll("&nbsp;","");

            article.setContent(s);
        }
        return articles;
    }
}
