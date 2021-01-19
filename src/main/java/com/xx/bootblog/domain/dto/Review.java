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
public class Review {

    private Integer id;

    private Integer articleId;

    private Integer userId;

    private String content;

    private Date reviewTime;

    private Integer parentReviewId;

    private Integer brotherReviewId;

}
