package com.xx.bootblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {


    private Integer id;

    private String name;

    private String email;

    private String password;

    private String avatar;

    private Date createTime;

    private Date lastLoginTime;

    private List<String> roles = new ArrayList<>();

    private List<String> authorities = new ArrayList<>();

    private Boolean status;

}
