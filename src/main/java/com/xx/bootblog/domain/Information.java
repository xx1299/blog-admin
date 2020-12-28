package com.xx.bootblog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Information {
    private Long articleTotal;
    private Long programaTotal;
    private Long dayView;
    private Date lastTime;
    private Long reviewTotal;
}
