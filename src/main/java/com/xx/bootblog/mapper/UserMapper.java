package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByEmail(String emailAddress);
}