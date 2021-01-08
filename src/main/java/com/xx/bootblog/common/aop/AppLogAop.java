package com.xx.bootblog.common.aop;


import com.google.gson.Gson;
import com.newland.installAdmin.config.annotation.AppControllerLog;
import com.newland.installAdmin.entity.pojo.SysAppLog;
import com.newland.installAdmin.service.ISysAppLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class AppLogAop {

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private ISysAppLogService logService;

    @Pointcut("@annotation(com.newland.installAdmin.config.annotation.AppControllerLog)")
    public void controllerAspect() {
    }


    AppControllerLog appControllerLog = null;

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        appControllerLog = getControllerMethodAnotation(joinPoint);
        log(joinPoint);
    }

    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
//        systemControllerLog = getControllerMethodAnotation(joinPoint);
    }

    public void log(JoinPoint joinPoint) {
        String userIp = request.getRemoteAddr();//请求的IP
        String requestUri = request.getRequestURI();//请求的Uri
        String method = request.getMethod();          //请求的方法类型(post/get)
        String title = appControllerLog.title();
        boolean needToken = appControllerLog.needToken();
        String userPhone = needToken ? request.getAttribute("phone").toString() : null;
        String requestArgs;
        if ("GET".equalsIgnoreCase(method)){
            requestArgs = request.getQueryString();
        }else {
            //文件上传的时候，使用fastjson报错
            requestArgs = new Gson().toJson(joinPoint.getArgs());
        }
        SysAppLog sysAppLog = SysAppLog.builder().createTime(new Date()).method(method).title(title).userPhone(userPhone)
                .params(requestArgs).userIp(userIp).url(requestUri).needCheck(needToken).build();
        CompletableFuture.runAsync(() -> {
                save(sysAppLog);
        });
    }

    public void save(SysAppLog log) {
        logService.save(log);
    }



    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }

    public static AppControllerLog getControllerMethodAnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AppControllerLog controllerLog = method
                .getAnnotation(AppControllerLog.class);
        return controllerLog;
    }


}