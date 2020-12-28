package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.Blogroll;
import java.util.List;

public interface BlogrollMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Blogroll record);

    Blogroll selectByPrimaryKey(Integer bid);

    List<Blogroll> selectAll();

    int updateByPrimaryKey(Blogroll record);
}