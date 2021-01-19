package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.po.ArticlePo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {

    List<ArticlePo> getAllArticle();

    void delete(Integer id);
}
