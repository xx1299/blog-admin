package com.xx.bootblog.web.controller;

import com.xx.bootblog.common.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping("/test")
    @SysLog(title = "测试")
    public String test(@RequestParam String name,String age) {
        int i = 1/0;
        return "测试";
    }
}
