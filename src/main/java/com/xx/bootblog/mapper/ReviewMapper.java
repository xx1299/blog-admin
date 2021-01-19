package com.xx.bootblog.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMapper {

    void deleteByArticleId(Integer articleId);
}
