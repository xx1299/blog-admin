package com.xx.bootblog.service;

import com.xx.bootblog.domain.User;
import org.apache.commons.mail.EmailException;

public interface UserService {

    User checkUser(User user) ;

    void saveCheckCode(String check, String emailAddress);

    String getCheckCode(String emailAddress);

    boolean emailIsExist(String emailAddress);

    Integer registUser(User user);

}
