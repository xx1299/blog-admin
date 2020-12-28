package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.AjxsRst;
import com.xx.bootblog.domain.User;
import com.xx.bootblog.service.UserService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }
    @GetMapping("regist")
    public String toRegist(){
        return "regist";
    }

    @RequestMapping("sendCheck")
    @ResponseBody
    public AjxsRst sendCheck(String emailAddress){
        AjxsRst ajxsRst = new AjxsRst();
        if (userService.emailIsExist(emailAddress)){
            ajxsRst.setSuccess(false);
            ajxsRst.setMsg("该邮箱已被注册");
        }else {
            try {
                HtmlEmail email = new HtmlEmail();
                StringBuilder checkCode = new StringBuilder();
                for (int i = 0;i<4;i++){
                    checkCode.append((int)(Math.random()*9));
                }
                System.out.println(emailAddress);
//        设置邮箱的smtp服务器
                email.setHostName("smtp.qq.com");
//        设置发送的字符集类型
                email.setCharset("UTF-8");
//        设置收件人
                email.addTo(emailAddress);
//        设置发件人的邮箱和用户名
                email.setFrom("489486921@qq.com","xxBlog");
//        设置邮箱地址和授权码
                email.setAuthentication("489486921@qq.com","peqwaenfskgabide");
                email.setSubject("您正在注册xxBlog");//设置发送主题
                email.setMsg("您的注册验证码为"+checkCode+",此验证码180秒内有效");//设置发送内容
                email.setSmtpPort(465);
                email.setSSLOnConnect(true);
                email.send();//进行发送
                userService.saveCheckCode(checkCode.toString(),emailAddress);
                ajxsRst.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                ajxsRst.setSuccess(false);
                ajxsRst.setMsg("验证码发送失败，请重新发送");
            }
        }
        return ajxsRst;
    }

    @PostMapping("regist")
    @ResponseBody
    public AjxsRst regist(User user, String check, HttpSession session){
        AjxsRst ajxsRst = new AjxsRst();
        String checkCode = userService.getCheckCode(user.getEmail());
        if (check.equals(checkCode)){
            Integer uid = userService.registUser(user);
            user.setUid(uid);
            session.setAttribute("user",user);
            ajxsRst.setSuccess(true);
        }else{
            ajxsRst.setSuccess(false);
            ajxsRst.setMsg("验证码错误");
        }
        return ajxsRst;
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }
        return "redirect:/index";
    }

    @PostMapping("login")
    @ResponseBody
    public Boolean login(User user, HttpSession session){
        user = userService.checkUser(user);
        if (user==null){
            return false;
        }else{
            session.setAttribute("user",user);
            return true;
        }
    }
}
