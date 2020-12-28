package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.Role;
import com.xx.bootblog.domain.params.AddRoleParams;
import com.xx.bootblog.domain.params.EditRoleParams;
import com.xx.bootblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public AjxsResponse<List<Role>> roles(){
        return AjxsResponse.success(roleService.getAllRole());
    }

    @PostMapping("/roles")
    public AjxsResponse addRole(@RequestBody AddRoleParams addRoleParams){
        roleService.addRole(addRoleParams);
        return AjxsResponse.success();
    }

    @DeleteMapping("/roles/{id}")
    public AjxsResponse delRole(@PathVariable("id") Integer id){
        roleService.delRole(id);
        return AjxsResponse.success();
    }

    @PutMapping("roles")
    public AjxsResponse editRole(@RequestBody EditRoleParams editRoleParams){
        roleService.editRole(editRoleParams);
        return AjxsResponse.success();
    }


}
