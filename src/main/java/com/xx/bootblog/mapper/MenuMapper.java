package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getChildren(Integer id);

    List<Menu> getTree();
}