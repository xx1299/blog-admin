package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.Programa;
import java.util.List;

public interface ProgramaMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Programa record);

    Programa selectByPrimaryKey(Integer pid);

    List<Programa> selectAll();

    int updateByPrimaryKey(Programa record);

    Long getTotal();
}