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
@Alias("ReviewPo")
public class ReviewPo {

    private Integer id;

    private Integer articleId;

    private Integer userId;

    private String content;

    private Date reviewTime;

    private Integer parentReviewId;

    private Integer brotherReviewId;

}
