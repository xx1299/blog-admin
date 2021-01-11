package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.AjxsResponse;
import com.xx.bootblog.domain.dto.Log;
import com.xx.bootblog.domain.dto.PageInfo;
import com.xx.bootblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/test")
    @SysLog(title = "测试")
    public String test(@RequestParam String name,String age) {
        int i = 1/0;
        return "测试";
    }

    @GetMapping("/logs")
    @SysLog(title = "获取日志分页列表")
    public AjxsResponse<PageInfo<Log>> logs(Integer pageSize, Integer pageNum,
                                            @RequestParam(required = false) String title,
                                            @RequestParam(required = false) String operatorEmail,
                                            @RequestParam(required = false) Date startTime,
                                            @RequestParam(required = false) Date endTime) {
        return AjxsResponse.success(logService.getLogByPage(pageSize,pageNum,title,operatorEmail,startTime,endTime));
    }
}
