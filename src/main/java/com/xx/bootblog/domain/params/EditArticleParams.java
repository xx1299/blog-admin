package com.xx.bootblog.domain.params;

import lombok.Data;

@Data
public class EditArticleParams {

    private Integer id;
    private String title;
    private String content;

}
