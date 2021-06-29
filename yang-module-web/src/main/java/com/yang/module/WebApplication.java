package com.yang.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.yang.module.dao.mapper")
@EnableAspectJAutoProxy
@ServletComponentScan
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
