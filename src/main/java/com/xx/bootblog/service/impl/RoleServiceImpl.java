package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.common.exception.CustomException;
import com.xx.bootblog.common.exception.ExceptionType;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.dto.Role;
import com.xx.bootblog.domain.params.AddRoleParams;
import com.xx.bootblog.domain.params.EditRoleParams;
import com.xx.bootblog.domain.po.AuthorityPo;
import com.xx.bootblog.domain.po.RolePo;
import com.xx.bootblog.mapper.AdminMapper;
import com.xx.bootblog.mapper.AuthorityMapper;
import com.xx.bootblog.mapper.RoleMapper;
import com.xx.bootblog.service.RoleService;
import com.xx.bootblog.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public PageInfo<Role> getRoleByPage(Integer pageSize, Integer pageNum) {

        Page<RolePo> page = PageHelper.startPage(pageNum, pageSize);
        roleMapper.getAllRole();
        return PageInfo.<Role>builder()
                .data(page.getResult().stream().map(it->DozerUtils.map(it,Role.class)).collect(Collectors.toList()))
                .total(page.getTotal())
                .pageNum(pageNum).pageSize(pageSize).build();
    }

    @Override
    @Transactional
    public void addRole(AddRoleParams addRoleParams) {
        RolePo rolePo = DozerUtils.map(addRoleParams, RolePo.class);
        roleMapper.insert(rolePo);
        addRoleParams.getAuthorityIds().forEach(authorityId->{
            roleMapper.insertRoleAuthority(rolePo.getId(),authorityId);
        });
    }

    @Override
    @Transactional
    public void delRole(Integer id) {
        if (!adminMapper.getAdminsByRoleId(id).isEmpty()){
            throw new CustomException(ExceptionType.SYSTEM_ERROR);
        }
        roleMapper.delete(id);
        roleMapper.deleteRoleAuthorityByRoleId(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRole()
                .stream()
                .map(it->{
                    Role role = DozerUtils.map(it, Role.class);
                    role.setAuthorityIds(authorityMapper.getAuthoritiesByRoleId(role.getId()).stream().map(AuthorityPo::getId).collect(Collectors.toList()));
                    return role;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void editRole(EditRoleParams editRoleParams) {
        roleMapper.deleteRoleAuthorityByRoleId(editRoleParams.getId());
        roleMapper.update(editRoleParams.getId(),editRoleParams.getName(),editRoleParams.getDesc());
        editRoleParams.getAuthorityIds().forEach(authorityId->{
            roleMapper.insertRoleAuthority(editRoleParams.getId(),authorityId);
        });
    }
}
