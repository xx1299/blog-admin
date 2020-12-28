package com.xx.bootblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    private Integer aid;

    private String title;

    private Integer praise;

    private Integer pageview;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releasetime;

    private String img;

    private Programa programa;

    private List<Review> reviews;

}