package com.xx.bootblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.bootblog.domain.dto.Log;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.domain.po.LogPo;
import com.xx.bootblog.mapper.LogMapper;
import com.xx.bootblog.service.LogService;
import com.xx.bootblog.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public void addLog(LogPo logPo) {
        logMapper.insert(logPo);
    }

    @Override
    public PageInfo<Log> getLogByPage(Integer pageSize, Integer pageNum, String title,
                                      String operatorEmail, Date startTime, Date endTime) {
        Page<LogPo> page = PageHelper.startPage(pageNum, pageSize);
        logMapper.getAllLog(title,operatorEmail,startTime,endTime);
        return PageInfo.<Log>builder().total(page.getTotal()).pageNum(pageNum).pageSize(pageSize)
                .data(DozerUtils.mapList(page.getResult(),Log.class))
                .build();
    }
}
