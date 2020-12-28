package com.xx.bootblog.service;

import com.xx.bootblog.domain.Blogroll;
import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.QueryVo;

import javax.management.Query;
import java.util.List;

public interface BlogrollService {

    void saveBlogroll(Blogroll blogroll);

    public List<Blogroll> blogrollList();

    void updateBlogroll(Blogroll blogroll);

    void deleteBlogroll(Integer bid);

    PageListResult blogrollPageList(QueryVo queryVo);
}
