package com.xx.bootblog.service;

import com.xx.bootblog.domain.dto.Log;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.po.LogPo;

import java.util.Date;

public interface LogService {


    void addLog(LogPo logPo);

    PageInfo<Log> getLogByPage(Integer pageSize, Integer pageNum, String title,
                               String operatorEmail, Date startTime, Date endTime);
}
