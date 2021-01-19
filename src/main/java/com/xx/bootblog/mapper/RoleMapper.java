package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.po.RolePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<RolePo> getRolesByAdminId(Integer id);

    List<RolePo> getAllRole();

    void insert(@Param("rolePo") RolePo rolePo);

    void delete(Integer id);

    void insertRoleAuthority(@Param("roleId") Integer roleId, @Param("authorityId") Integer authorityId);

    void deleteRoleAuthorityByRoleId(Integer roleId);

    void update(@Param("id") Integer id, @Param("name") String name, @Param("desc") String desc);
}
