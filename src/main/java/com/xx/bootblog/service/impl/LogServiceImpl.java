package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.po.LogPo;
import com.xx.bootblog.mapper.LogMapper;
import com.xx.bootblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public void addLog(LogPo logPo) {
        logMapper.insert(logPo);
    }
}
