package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ArticleMapper {

    int deleteByPrimaryKey(Integer aid);

    int insert(Article record);

    Article selectByPrimaryKey(Integer aid);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> selectArticleByPid(Integer pid);

    List<Article> selectArticleByTime();

    Long getTotal();

    Date getLastTime();

    Long getTotalByPid(Integer pid);

    void updatePraiseByAid(Integer aid);

    void updatePageview(Integer aid);

    Article selectPreArticle(@Param("aid") Integer aid, @Param("pid") Integer pid);

    Article selectSufArticle(@Param("aid") Integer aid, @Param("pid") Integer pid);

    List<Article> selectByContent(String keyWord);

    List<Article> selectByTitle(String keyWord);


}