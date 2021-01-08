package com.xx.bootblog.domain.dto;

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
@Alias("LogPo")
public class Log {

    private Integer id;
    private String ip;
    private String url;
    private String params;
    private String title;
    private String operatorEmail;
    private String method;
    private Date createTime;
    private String error;
    private Integer type;

}
