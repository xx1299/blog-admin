package com.xx.bootblog.web.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;

@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(String address){
        HtmlEmail email = new HtmlEmail();
        StringBuilder code = new StringBuilder();
        for (int i = 0;i<4;i++){
            code.append((int)(Math.random()*9));
        }
        System.out.println(address);
        try {
//        设置邮箱的smtp服务器
        email.setHostName("smtp.qq.com");
//        设置发送的字符集类型
        email.setCharset("UTF-8");
//        设置收件人
        email.addTo(address);
//        设置发件人的邮箱和用户名
        email.setFrom("489486921@qq.com","xxBlog");
//        设置邮箱地址和授权码
        email.setAuthentication("489486921@qq.com","peqwaenfskgabide");
        email.setSubject("您正在注册xxBlog");//设置发送主题
        email.setMsg("您的验证码为"+code+",此验证码60秒内有效");//设置发送内容
        email.send();//进行发送
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code.toString();
    }
}
