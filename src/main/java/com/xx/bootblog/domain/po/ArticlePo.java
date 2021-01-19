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
@Alias("ArticlePo")
public class ArticlePo {

    private Integer id;

    private String title;

    private String content;

    private Date realeaseTime;

    private Integer userId;

}
