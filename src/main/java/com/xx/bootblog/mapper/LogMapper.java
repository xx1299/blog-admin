package com.xx.bootblog.mapper;


import com.xx.bootblog.domain.po.LogPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogMapper {

    void insert(@Param("logPo") LogPo logPo);

    List<LogPo> getAllLog(@Param("title") String title, @Param("operatorEmail") String operatorEmail,
                          @Param("type") String type, @Param("startTime") Date startTime,
                          @Param("endTime") Date endTime);

    void delete(Integer id);
}
