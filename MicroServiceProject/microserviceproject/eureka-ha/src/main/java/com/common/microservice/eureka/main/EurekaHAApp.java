package com.common.microservice.eureka.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by jackiechan on 18-7-30/上午11:30
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaHAApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaHAApp.class, args);
    }
}
