package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.User;
import com.xx.bootblog.mapper.UserMapper;
import com.xx.bootblog.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        User u = userMapper.selectByEmail(user.getEmail());
        if (u!=null){
            if (user.getPassword().equals(u.getPassword())){
                return u;
            }
        }
        return null;
    }

    @Override
    public void saveCheckCode(String check,String emailAddress) {
        redisTemplate.opsForValue().set(emailAddress,check);
        redisTemplate.expire(emailAddress,180, TimeUnit.SECONDS);
    }

    @Override
    public String getCheckCode(String emailAddress) {
        String checkCode = (String)redisTemplate.opsForValue().get(emailAddress);
        return checkCode;
    }

    @Override
    public boolean emailIsExist(String emailAddress) {
        User user = userMapper.selectByEmail(emailAddress);
        if (user!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer registUser(User user) {
        StringBuilder headimg = new StringBuilder("/img/headimg/");
        int random = (int)(Math.random()*24);
        headimg.append(random).append(".png");
        user.setHeadimg(headimg.toString());
        Integer insert = userMapper.insert(user);
        return insert;
    }
}
