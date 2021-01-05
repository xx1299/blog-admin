package com.xx.bootblog.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("AdminPo")
public class AdminPo {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String avatar;

    private Date createTime;

    private Date lastLoginTime;

    private Boolean status;

}
