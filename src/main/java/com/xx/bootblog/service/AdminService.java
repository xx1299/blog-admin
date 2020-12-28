package com.xx.bootblog.service;

import com.xx.bootblog.domain.dto.Admin;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.dto.Token;
import com.xx.bootblog.domain.dto.UserInfo;
import com.xx.bootblog.domain.params.LoginParams;

import java.util.List;

public interface AdminService {
    UserInfo getUserInfoByid(Integer id);

    Token login(LoginParams loginParams);

    UserInfo info(String token);

    PageInfo<Admin> getAdminByPage(Integer pageSize, Integer pageNum);

    void changeStatus(Boolean status, Integer id);

    void logout();

    void assginAdminRole(Integer id, List<Integer> roleIds);
}
