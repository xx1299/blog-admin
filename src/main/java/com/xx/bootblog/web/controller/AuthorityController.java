package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.Authority;
import com.xx.bootblog.domain.params.AddAuthorityParams;
import com.xx.bootblog.domain.params.EditAuthorityParams;
import com.xx.bootblog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @SysLog(title = "获取权限树形列表")
    @GetMapping("/authorities/tree")
    public AjxsResponse<List<Authority>> getAuthorityTree(){
        return AjxsResponse.success(authorityService.getAuthorityTree());
    }

    @SysLog(title = "获取权限列表")
    @GetMapping("/authorities")
    public AjxsResponse<List<Authority>> getAllAuthority(){
        return AjxsResponse.success(authorityService.getAllAuthority());
    }

    @SysLog(title = "添加权限")
    @PostMapping("/authorities")
    public AjxsResponse addAuthority(@RequestBody AddAuthorityParams addAuthorityParams){
        authorityService.addAuthority(addAuthorityParams);
        return AjxsResponse.success();
    }

    @SysLog(title = "编辑权限")
    @PutMapping("/authorities")
    public AjxsResponse editAuthority(@RequestBody EditAuthorityParams editAuthorityParams){
        authorityService.editAuthority(editAuthorityParams);
        return AjxsResponse.success();
    }

    @SysLog(title = "删除权限")
    @DeleteMapping("/authorities/{id}")
    public AjxsResponse delAuthority(@PathVariable("id") Integer id){
        authorityService.delAuthority(id);
        return AjxsResponse.success();
    }

}
