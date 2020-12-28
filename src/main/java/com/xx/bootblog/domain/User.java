package com.xx.bootblog.domain;

import lombok.Data;

@Data
public class User {
    private Integer uid;

    private String uname;

    private String email;

    private String password;

    private String headimg;

}