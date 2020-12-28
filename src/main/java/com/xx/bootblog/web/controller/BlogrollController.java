package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.AjxsRst;
import com.xx.bootblog.domain.Blogroll;
import com.xx.bootblog.domain.PageListResult;
import com.xx.bootblog.domain.QueryVo;
import com.xx.bootblog.service.BlogrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogrollController {

    @Autowired
    BlogrollService blogrollService;

    @RequestMapping("blogroll")
    public String blogroll(){
        System.out.println(111);
        return "blogroll";
    }

    @RequestMapping("blogrollPageList")
    @ResponseBody
    public PageListResult blogrollPageList(QueryVo queryVo){
        return blogrollService.blogrollPageList(queryVo);
    }

    @RequestMapping("deleteBlogroll")
    @ResponseBody
    public AjxsRst deleteBlogroll(Integer bid){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            blogrollService.deleteBlogroll(bid);
            ajxsRst.setMsg("删除成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("删除失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }

    @RequestMapping("updateBlogroll")
    @ResponseBody
    public AjxsRst updateBlogroll(Blogroll blogroll){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            blogrollService.updateBlogroll(blogroll);
            ajxsRst.setMsg("更新成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("更新失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }

    @RequestMapping("saveBlogroll")
    @ResponseBody
    public AjxsRst saveBlogroll(Blogroll blogroll){
        AjxsRst ajxsRst = new AjxsRst();
        try {
            blogrollService.saveBlogroll(blogroll);
            ajxsRst.setMsg("添加成功");
            ajxsRst.setSuccess(true);
        }catch (Exception e){
            ajxsRst.setMsg("添加失败");
            ajxsRst.setSuccess(false);
        }finally {
            return ajxsRst;
        }
    }

}
