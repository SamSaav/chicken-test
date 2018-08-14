package com.accenture.microservice.microchickenserver.controller;

import com.accenture.microservice.microchickenserver.model.Chicken;
import com.accenture.microservice.microchickenserver.service.ChickenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ChickenController {

    @Autowired
    ChickenService chickenService;

    /*@LoadBalanced
    @Bean
    public RestTemplate RestTemplate (RestTemplateBuilder builder){
        return builder.build();
    }*/

    @Autowired
    RestTemplate restTemplate;

    //	Metodo para obtener todas las granjas
    @RequestMapping(value = "/chickens", method = RequestMethod.GET)
    @ResponseBody
    public List<Chicken> getAllEggs() {
        List<Chicken> lstChickens = chickenService.getAllChickens();
        return lstChickens;
    }

    //	Metodo para obtener una granja espesifica
    @RequestMapping(value = "/chickens/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Chicken getChicken(@PathVariable("id") Integer id) {
        Chicken chicken = chickenService.getChicken(id);
        return chicken;
    }

    @RequestMapping(value = "/chickens/chicken/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getChickensWithEggs(@PathVariable("id") Integer id){
        Map<String, Object> getObjects = chickenService.getChickeWithEggs(id);
        return getObjects;
    }

    @RequestMapping(value = "chickens/chicken/{id}/farm", method = RequestMethod.GET)
    @ResponseBody
    public Object getFarm(@PathVariable("id") Integer id){
        String url = "http://microservice-farm-server/farms/" + id;
        Object farm = restTemplate.getForObject(url, Object.class);
        return farm;
    }

    @RequestMapping(value = "chickens/chicken/{id}/eggs", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getChickens(@PathVariable("id") Integer id){
        Chicken chicken = chickenService.getChicken(id);
        List<Integer> lstEgg = chicken.getEggs_id();
        List<Object> eggs = new ArrayList<>();
        for(Integer eggID : lstEgg){
            String url = "http://microservice-egg-server/eggs/" + eggID;
            Object egg = restTemplate.getForObject(url, Object.class);
            eggs.add(egg);
        }
        return eggs;
    }

    //	Metodo para guardar una granja
    @RequestMapping(value = "/chickens/chicken", method = RequestMethod.POST)
    public ResponseEntity<?> saveChicken(@RequestBody Chicken chicken) {
        if (chickenService.saveChicken(chicken)) {
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido", HttpStatus.NO_CONTENT);
    }

    //	Metodo para modificar una granja
    @RequestMapping(value = "/chickens/{id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateChicken(@RequestBody Chicken chicken) {
        if (chickenService.updateChicken(chicken)) {
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido", HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar una granja
    @RequestMapping(value = "/chickens/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteChicken(@PathVariable("id") Integer id) {
        Chicken chicken = chickenService.getChicken(id);
        if (chickenService.deleteChicken(chicken)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido", HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar el ID de una granja
    @RequestMapping(value = "chickens/{id}/deleteId", method = RequestMethod.GET)
    public ResponseEntity<?> deleteIdChicken(@PathVariable("id") Integer id) {
        if (chickenService.deleteChickenId(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido", HttpStatus.NO_CONTENT);
    }

}
