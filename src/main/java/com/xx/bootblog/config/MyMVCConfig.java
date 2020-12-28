package com.xx.bootblog.config;


import com.xx.bootblog.web.interceptor.ArticleInterceptor;
import com.xx.bootblog.web.interceptor.GetIPInterceptor;
import com.xx.bootblog.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    @Bean
    public GetIPInterceptor getIPInterceptor() {
        return new GetIPInterceptor();
    }

    @Bean
    public ArticleInterceptor articleInterceptor(){
        return new ArticleInterceptor();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("admin").setViewName("admin");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin","/release","/blogroll","/programa");
        registry.addInterceptor(getIPInterceptor()).addPathPatterns("/","/index","/section/*");
        registry.addInterceptor(articleInterceptor()).addPathPatterns("/article/*");
    }
}
