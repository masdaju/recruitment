package com.cg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.cg.mapper")
public class RecruitmentApplication {

    public static void main(String[] args){
        SpringApplication.run(RecruitmentApplication.class, args);
    }

}
