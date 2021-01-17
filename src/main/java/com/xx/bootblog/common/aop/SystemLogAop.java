package com.xx.bootblog.common.aop;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.bootblog.common.LogEnum;
import com.xx.bootblog.common.annotation.SysLog;
import com.xx.bootblog.domain.dto.UserInfo;
import com.xx.bootblog.domain.po.LogPo;
import com.xx.bootblog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class SystemLogAop {

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.xx.bootblog.common.annotation.SysLog)")
    public void controllerAspect() {
    }


    SysLog sysLog = null;

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        sysLog = getControllerMethodAnotation(point);
        Object object = null;
        String error = "";
        try {
            object = point.proceed();
        } catch (Exception e) {
            error  = getExceptionText(e);
            throw e;
        }finally {
            log(point,error);
        }
        return object;
    }

    public void log(JoinPoint joinPoint,String error) {
        String userIp = request.getRemoteAddr();//请求的IP
        String requestUri = request.getRequestURI();//请求的Uri
        String method = request.getMethod();          //请求的方法类型(post/get)
        String title = sysLog.title();
        String requestArgs = "";
        if ("GET".equalsIgnoreCase(method)){
            requestArgs = request.getQueryString();
        }else {
            try {
                requestArgs = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (SecurityUtils.getSubject().isAuthenticated()) {
            UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
            String email = userInfo.getEmail();
            LogPo logPo = LogPo.builder()
                    .ip(userIp).operatorEmail(email).url(requestUri)
                    .method(method).params(requestArgs).title(title).createTime(new Date())
                    .error(error).type(LogEnum.SYSTEM_LOG.getKey())
                    .build();
            CompletableFuture.runAsync(() -> {
                save(logPo);
            });
        }
    }

    public void save(LogPo log) {
        logService.addLog(log);
    }



    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        e.printStackTrace();
    }

    public static SysLog getControllerMethodAnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog controllerLog = method
                .getAnnotation(SysLog.class);
        return controllerLog;
    }

    public static String getExceptionText(Exception e){
        String text = "" ;
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        text = sw.toString();
        return text;
    }


}