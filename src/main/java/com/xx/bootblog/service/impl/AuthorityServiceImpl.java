package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.dto.Authority;
import com.xx.bootblog.domain.params.AddAuthorityParams;
import com.xx.bootblog.domain.params.EditAuthorityParams;
import com.xx.bootblog.domain.po.AuthorityPo;
import com.xx.bootblog.mapper.AuthorityMapper;
import com.xx.bootblog.service.AuthorityService;
import com.xx.bootblog.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public List<Authority> getAuthorityTree() {
        return authorityMapper.getAuthorityTree();
    }

    @Override
    public List<Authority> getAllAuthority() {
        return DozerUtils.mapList(authorityMapper.getAllAuthority(), Authority.class);
    }

    @Override
    public void addAuthority(AddAuthorityParams addAuthorityParams) {
        authorityMapper.insert(DozerUtils.map(addAuthorityParams, AuthorityPo.class));
    }

    @Override
    public void editAuthority(EditAuthorityParams editAuthorityParams) {
        authorityMapper.update(editAuthorityParams.getId(),editAuthorityParams.getName(),
                editAuthorityParams.getIdentifier(),editAuthorityParams.getParentId());
    }
}
