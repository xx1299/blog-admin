package com.xx.bootblog.mapper;


import com.xx.bootblog.domain.po.LogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {

    void insert(@Param("logPo") LogPo logPo);

    List<LogPo> getAllLog();
}
