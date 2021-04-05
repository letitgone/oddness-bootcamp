package com.oddness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.oddness.mapper")
@SpringBootApplication
public class oddnessMpSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(oddnessMpSpringbootApplication.class, args);
    }

}
