package com.xx.bootblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {

    private Long total;
    private Integer pageSize;
    private Integer pageNum;
    private List<T> data = new ArrayList<>();

}
