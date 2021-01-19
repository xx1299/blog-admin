package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.Article;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @SysLog(title = "获取文章分页列表")
    @GetMapping("/articles")
    public AjxsResponse<PageInfo<Article>> articles(Integer pageSize,Integer pageNum){
        return AjxsResponse.success(articleService.getArticleByPage(pageSize,pageNum));
    }

    @SysLog(title = "删除文章")
    @DeleteMapping("/articles/{id}")
    public AjxsResponse delArticle(@PathVariable Integer id){
        articleService.delArticle(id);
        return AjxsResponse.success();
    }

    @SysLog(title = "发布文章")
    @PostMapping("/articles")
    public AjxsResponse addArticle(){
        return AjxsResponse.success();
    }

    @SysLog(title = "编辑文章")
    @PutMapping("/articles")
    public AjxsResponse editArticle(){
        return AjxsResponse.success();
    }

}
