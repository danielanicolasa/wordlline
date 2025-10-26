package com.example.worldlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class WorldLineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorldLineTestApplication.class, args);
    }

}
