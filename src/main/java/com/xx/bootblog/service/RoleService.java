package com.xx.bootblog.service;

import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.dto.Role;
import com.xx.bootblog.domain.params.AddRoleParams;
import com.xx.bootblog.domain.params.EditRoleParams;

import java.util.List;

public interface RoleService {


    PageInfo<Role> getRoleByPage(Integer pageSize, Integer pageNum);

    void addRole(AddRoleParams addRoleParams);

    void delRole(Integer id);

    List<Role> getAllRole();

    void editRole(EditRoleParams editRoleParams);
}
