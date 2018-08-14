package com.accenture.microservice.microchickentest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroChickenTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChickenTestApplication.class, args);
    }

}
