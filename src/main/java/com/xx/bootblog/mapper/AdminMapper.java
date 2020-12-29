package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.po.AdminPo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdminMapper {
    Optional<AdminPo> getAdminById(Integer id);

    Optional<AdminPo> getAdminByUserName(String name);

    void updateLastLoginTime(@Param("id") Integer id, @Param("lastLoginTime") Date lastLoginTime);

    List<AdminPo> getAllAdmin();

    void updateStatus(@Param("status") Boolean status, @Param("id") Integer id);

    void deleteAdminAllRole(Integer id);

    void insertAdminRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);

    List<AdminPo> getAdminsByRoleId(Integer roleId);
}
