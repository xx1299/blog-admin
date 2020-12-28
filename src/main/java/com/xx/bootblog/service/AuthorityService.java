package com.xx.bootblog.service;

import com.xx.bootblog.domain.dto.Authority;
import com.xx.bootblog.domain.params.AddAuthorityParams;
import com.xx.bootblog.domain.params.EditAuthorityParams;

import java.util.List;

public interface AuthorityService {
    List<Authority> getAuthorityTree();

    List<Authority> getAllAuthority();

    void addAuthority(AddAuthorityParams addAuthorityParams);

    void editAuthority(EditAuthorityParams editAuthorityParams);
}
