package com.accenture.microservice.microchickentest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MicroserviceChickenTestController {

    @LoadBalanced
    @Bean
    public RestTemplate RestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test/{id}")
    @ResponseBody
    public Map<String, Object> showThisFarm(@PathVariable("id") Integer id){
        String url = "http://microservice-farm-server/farms/farm/" + id;
        Map<String, Object> farm = new LinkedHashMap<>();
        farm.put("farm", restTemplate.getForObject(url, Object.class));
        return farm;
    }

}
