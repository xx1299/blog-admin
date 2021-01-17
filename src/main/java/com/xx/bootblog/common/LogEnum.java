package com.xx.bootblog.common;

public enum LogEnum {

    SYSTEM_LOG("后台系统日志",1),
    BLOG_LOG("博客日志",2),
    ;

    private String type;
    private Integer key;

    LogEnum(String type,Integer key){
        this.type = type;
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public String getType() {
        return type;
    }
}
