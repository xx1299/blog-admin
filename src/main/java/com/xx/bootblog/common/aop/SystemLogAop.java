package com.xx.bootblog.common.aop;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class SystemLogAop {

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private ISysLogService logService;

    @Pointcut("@annotation(com.newland.installAdmin.config.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }


    SystemControllerLog systemControllerLog = null;

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        systemControllerLog = getControllerMethodAnotation(joinPoint);
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
        String title = systemControllerLog.title();
        String requestArgs;
        if ("GET".equalsIgnoreCase(method)){
            requestArgs = request.getQueryString();
        }else {
            //文件上传的时候，使用fastjson报错
            requestArgs = new Gson().toJson(joinPoint.getArgs());
        }
        if (SecurityUtils.getSubject().isAuthenticated()) {
            Principal principal = (Principal) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            String userName = principal.getUserName();
            String userOrganizationId = principal.getOrganizationId();
            SysLog log = SysLog.builder()
                    .userIp(userIp).userName(userName).url(requestUri)
                    .method(method).params(requestArgs).title(title).createTime(new Date())
                    .organizationId(Long.valueOf(userOrganizationId))
                    .build();
            CompletableFuture.runAsync(() -> {
                save(log);
            });
        }
    }

    public void save(SysLog log) {
        logService.save(log);
    }



    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }

    public static SystemControllerLog getControllerMethodAnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method
                .getAnnotation(SystemControllerLog.class);
        return controllerLog;
    }


}