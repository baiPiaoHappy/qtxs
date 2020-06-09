package com.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 启动类
 * @author: 小猴子
 * @date: 2019-11-07 9:30
 */

@SpringBootApplication
@ComponentScan("com.base.*")
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper,com.base.mapper.*")
public class blApplication {
    public static void main(String[] args) {
        SpringApplication.run(blApplication.class,args);
    }
}
