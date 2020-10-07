package com.gzy.leeboo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("com.gzy.leeboo.mapper")
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class LeebooApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeebooApplication.class, args);
    }
}
