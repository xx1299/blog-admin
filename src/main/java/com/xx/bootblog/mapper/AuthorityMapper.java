package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.dto.Authority;
import com.xx.bootblog.domain.po.AuthorityPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityMapper {
    List<AuthorityPo> getAuthoritiesByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Authority> getAuthorityTree();

    List<Authority> getAuthoritiesByParentId(Integer parentId);

    List<AuthorityPo> getAllAuthority();

    void insert(@Param("authorityPo") AuthorityPo authorityPo);

    void update(@Param("id") Integer id, @Param("name") String name, @Param("identifier") String identifier, @Param("parentId") Integer parentId);

    void delete(Integer id);

    void deleteByParentId(Integer parentId);

    void deleteRoleAuthorityByAuthorityId(Integer authorityId);

    List<AuthorityPo> getAuthoritiesByRoleId(Integer roleId);
}
