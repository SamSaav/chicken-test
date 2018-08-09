package com.accenture.microservice.microfarmserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroFarmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroFarmServerApplication.class, args);
    }
}
