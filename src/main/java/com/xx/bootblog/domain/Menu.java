package com.xx.bootblog.domain;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String text;

    private String url;

    private Menu parent;

    private List<Menu> children;

}