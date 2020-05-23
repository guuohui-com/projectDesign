package com.ysu.graduationproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ysu.graduationproject.dao")
public class GraduationprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationprojectApplication.class, args);
    }

}
