package com.xx.bootblog.common;

public enum LogEnum {

    ERROR_LOG("异常日志",0),
    NORMAL_LOG("正常日志",1),
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
