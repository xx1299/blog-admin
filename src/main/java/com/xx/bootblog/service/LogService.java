package com.xx.bootblog.service;

import com.xx.bootblog.domain.po.LogPo;
import org.springframework.stereotype.Service;

public interface LogService {


    void addLog(LogPo logPo);
}
