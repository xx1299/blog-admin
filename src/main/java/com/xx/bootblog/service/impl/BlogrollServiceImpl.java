package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.domain.Blogroll;
import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.QueryVo;
import com.xx.bootblog.mapper.BlogrollMapper;
import com.xx.bootblog.service.BlogrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
@Service
public class BlogrollServiceImpl implements BlogrollService {

    @Autowired
    private BlogrollMapper blogrollMapper;

    @Override
    public void saveBlogroll(Blogroll blogroll) {
        blogrollMapper.insert(blogroll);
    }

    @Override
    public List<Blogroll> blogrollList() {
        List<Blogroll> blogrolls = blogrollMapper.selectAll();
        return blogrolls;
    }

    @Override
    public void updateBlogroll(Blogroll blogroll) {
        blogrollMapper.updateByPrimaryKey(blogroll);
    }

    @Override
    public void deleteBlogroll(Integer bid) {
        blogrollMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public PageListResult blogrollPageList(QueryVo queryVo) {
        PageListResult pageListResult = new PageListResult();
        Page<Blogroll> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Blogroll> blogrolls = blogrollMapper.selectAll();
        pageListResult.setRows(blogrolls);
        pageListResult.setTotal(page.getTotal());
        return pageListResult;
    }
}
