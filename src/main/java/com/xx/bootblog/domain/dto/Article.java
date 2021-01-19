package com.xx.bootblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Integer id;

    private String title;

    private String content;

    private Date realeaseTime;

    private Integer userId;

}
