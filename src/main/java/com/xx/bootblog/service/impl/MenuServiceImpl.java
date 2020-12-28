package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.Menu;
import com.xx.bootblog.mapper.MenuMapper;
import com.xx.bootblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getTree() {
        return menuMapper.getTree();
    }
}
