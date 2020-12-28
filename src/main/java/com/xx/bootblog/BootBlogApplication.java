package com.xx.bootblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xx.bootblog.mapper")
public class BootBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBlogApplication.class, args);
    }

}
