package com.elderdo.demo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.elderdo.demo_api")
@EnableJpaRepositories(basePackages = "com.elderdo.demo_api.data") // Forces scanning of the data interface definitions
@EntityScan(basePackages = "com.elderdo.demo_api.entities") // Forces scanning of your persistence models
public class DemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApiApplication.class, args);
    }
}
