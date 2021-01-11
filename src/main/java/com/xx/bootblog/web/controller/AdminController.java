package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.Admin;
import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.dto.Token;
import com.xx.bootblog.domain.params.LoginParams;
import com.xx.bootblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/adminLogin")
    @SysLog(title = "登录")
    public AjxsResponse<Token> login(@RequestBody LoginParams loginParams){
        return AjxsResponse.success(adminService.login(loginParams));
    }

    @SysLog(title = "退出")
    @PostMapping("/adminLogout")
    public AjxsResponse logout(){
        adminService.logout();
        return AjxsResponse.success();
    }

    @SysLog(title = "获取当前用户信息")
    @GetMapping("/adminInfo")
    public AjxsResponse<Admin> info(String token){
        return AjxsResponse.success(adminService.info(token));
    }

    @SysLog(title = "获取用户分页列表")
    @GetMapping("/admins")
    public AjxsResponse<PageInfo<Admin>> admins(Integer pageSize, Integer pageNum){
        return AjxsResponse.success(adminService.getAdminByPage(pageSize,pageNum));
    }

    @SysLog(title = "修改用户状态")
    @PutMapping("/admins/{id}/status")
    public AjxsResponse changeStatus(@PathVariable("id") Integer id ,Boolean status){
        adminService.changeStatus(status,id);
        return AjxsResponse.success();
    }

    @SysLog(title = "获取用户角色")
    @PutMapping("/admins/{id}/roles")
    public AjxsResponse assginAdminRole(@PathVariable("id") Integer id,@RequestBody List<Integer> roleIds){
        adminService.assginAdminRole(id,roleIds);
        return AjxsResponse.success();
    }


}
