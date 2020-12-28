package com.xx.bootblog.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageListResult {
    private Long Total;
    private List<?> rows = new ArrayList<>();
}
