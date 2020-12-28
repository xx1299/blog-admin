package com.xx.bootblog.web.interceptor;

import com.xx.bootblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleInterceptor implements HandlerInterceptor {

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession();
        List<Integer> read = (List)session.getAttribute("view");
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer aid = Integer.parseInt((String)pathVariables.get("aid"));
        if (read==null){
            read = new ArrayList();
            read.add(aid);
            articleService.updatePageview(aid);
            session.setAttribute("view",read);
        }else{
            int hasread = 0;
            for (Integer r : read) {
                if (r.equals(aid)){
                    hasread = 1;
                }
            }
            if (hasread == 0){
                read.add(aid);
                articleService.updatePageview(aid);
                session.setAttribute("view",read);
            }
        }

        return true;
    }
}
