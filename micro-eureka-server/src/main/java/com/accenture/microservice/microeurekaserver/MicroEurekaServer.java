package com.accenture.microservice.microeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(MicroEurekaServer.class, args);
    }
}
