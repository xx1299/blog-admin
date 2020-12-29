package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.common.exception.CustomException;
import com.xx.bootblog.common.exception.ExceptionType;
import com.xx.bootblog.domain.dto.*;
import com.xx.bootblog.domain.params.LoginParams;
import com.xx.bootblog.domain.po.AdminPo;
import com.xx.bootblog.domain.po.AuthorityPo;
import com.xx.bootblog.domain.po.RolePo;
import com.xx.bootblog.mapper.AdminMapper;
import com.xx.bootblog.mapper.AuthorityMapper;
import com.xx.bootblog.mapper.RoleMapper;
import com.xx.bootblog.service.AdminService;
import com.xx.bootblog.utils.DozerUtils;
import com.xx.bootblog.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

//    @Autowired
//    DozerBeanMapper mapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserInfo getUserInfoByid(Integer id) {

        UserInfo userInfo = adminMapper.getAdminById(id)
                .map(adminPo -> DozerUtils.map(adminPo, UserInfo.class))
                .orElseThrow(()->new CustomException(ExceptionType.RESOURCE_NOT_FOUND));
        List<RolePo> roles = roleMapper.getRolesByAdminId(id);
        List<AuthorityPo> authorities = authorityMapper.getAuthoritiesByRoleIds(roles.stream()
                .map(RolePo::getId).collect(Collectors.toList()));
        userInfo.setRoles(roles.stream().map(RolePo::getName).collect(Collectors.toList()));
        userInfo.setAuthorities(authorities.stream().map(AuthorityPo::getIdentifier).collect(Collectors.toList()));
        return userInfo;
    }

    @Override
    public Token login(LoginParams loginParams) {

        AdminPo adminPo = adminMapper.getAdminByUserName(loginParams.getName())
                .orElseThrow(() -> new CustomException(ExceptionType.RESOURCE_NOT_FOUND, "该用户不存在"));
        if (!adminPo.getPassword().equals(loginParams.getPassword())){
            throw new CustomException(ExceptionType.USER_INPUT_ERROR,"密码输入错误");
        }
        String token = JwtUtils.createToken(adminPo);
        redisTemplate.opsForValue().set(adminPo.getId().toString(),token);
        adminMapper.updateLastLoginTime(adminPo.getId(),new Date());
        return Token.builder().token(token).build();

    }

    @Override
    public UserInfo info(String token) {

        Integer id = JwtUtils.getClaims(token).getClaim("id").asInt();
//        Admin admin = adminMapper.getAdminById(id).map(adminPo -> DozerUtils.map(adminPo, Admin.class))
//                .orElseThrow(() -> new CustomException(ExceptionType.RESOURCE_NOT_FOUND, "该用户不存在"));
        return getUserInfoByid(id);

    }

    @Override
    public PageInfo<Admin> getAdminByPage(Integer pageSize, Integer pageNum) {
        Page<AdminPo> page = PageHelper.startPage(pageNum, pageSize);
        adminMapper.getAllAdmin();
        List<Admin> admins = page.getResult()
                .stream()
                .map(adminPo -> {
                    Admin admin = DozerUtils.map(adminPo, Admin.class);
                    List<Role> roles= roleMapper.getRolesByAdminId(admin.getId())
                            .stream()
                            .map(rolePo -> DozerUtils.map(rolePo, Role.class))
                            .collect(Collectors.toList());
                    admin.setRoles(roles);
                    return admin;
                })
                .collect(Collectors.toList());
        return PageInfo.<Admin>builder().data(admins).total(page.getTotal())
                .pageNum(pageNum).pageSize(pageSize).build();
    }

    @Override
    public void changeStatus(Boolean status, Integer id) {
        adminMapper.updateStatus(status,id);
    }

    @Override
    public void logout() {
        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        redisTemplate.delete(userInfo.getId());
    }

    @Override
    @Transactional
    public void assginAdminRole(Integer id, List<Integer> roleIds) {
        adminMapper.deleteAdminAllRole(id);
        roleIds.forEach(roleId->{
            adminMapper.insertAdminRole(id,roleId);
        });
    }
}
