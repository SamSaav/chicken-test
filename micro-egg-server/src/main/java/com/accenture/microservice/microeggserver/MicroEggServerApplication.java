package com.accenture.microservice.microeggserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroEggServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroEggServerApplication.class, args);
    }
}
