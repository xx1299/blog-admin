package com.xx.bootblog.mapper;


import com.xx.bootblog.domain.po.LogPo;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {

    void insert(@Param("logPo") LogPo logPo);
}
