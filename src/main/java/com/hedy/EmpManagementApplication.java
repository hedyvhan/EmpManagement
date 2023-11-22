package com.hedy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Slf4j
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")

public class EmpManagementApplication {

    public static void main(String[] args) {
        log.info("---system start!---");
        SpringApplication.run(EmpManagementApplication.class, args);
    }

}
