package com.xx.bootblog.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");
        System.out.println(admin);
        if (admin==null){
            request.setAttribute("error","请先登录");
            request.getRequestDispatcher("/adminLogin").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
