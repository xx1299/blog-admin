package com.xx.bootblog.web.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GetIPInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        String accessIp = null;
        if (request!=null){
            accessIp = request.getRemoteAddr()+today;
        }
        if (redisTemplate.hasKey(today)){
            redisTemplate.opsForHyperLogLog().add(today,accessIp);
        }else{
            redisTemplate.opsForHyperLogLog().add(today,accessIp);
            redisTemplate.expire(today,24, TimeUnit.HOURS);
        }
        return true;
    }
}
