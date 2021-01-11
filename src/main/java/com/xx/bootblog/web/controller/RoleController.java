package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.Role;
import com.xx.bootblog.domain.params.AddRoleParams;
import com.xx.bootblog.domain.params.EditRoleParams;
import com.xx.bootblog.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RoleController {

    @Autowired
    RoleService roleService;

    @SysLog(title = "获取角色列表")
    @GetMapping("/roles")
    public AjxsResponse<List<Role>> roles(){
        return AjxsResponse.success(roleService.getAllRole());
    }

    @SysLog(title = "添加角色")
    @PostMapping("/roles")
    public AjxsResponse addRole(@RequestBody AddRoleParams addRoleParams){
        log.info("addRoleParams:{}",addRoleParams);
        roleService.addRole(addRoleParams);
        return AjxsResponse.success();
    }

    @SysLog(title = "删除角色")
    @DeleteMapping("/roles/{id}")
    public AjxsResponse delRole(@PathVariable("id") Integer id){
        log.info("id:{}",id);
        roleService.delRole(id);
        return AjxsResponse.success();
    }

    @SysLog(title = "编辑角色")
    @PutMapping("roles")
    public AjxsResponse editRole(@RequestBody EditRoleParams editRoleParams){
        log.info("editRoleParams:{}",editRoleParams);
        roleService.editRole(editRoleParams);
        return AjxsResponse.success();
    }


}
