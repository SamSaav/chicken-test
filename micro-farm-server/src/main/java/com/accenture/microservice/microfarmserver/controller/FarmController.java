package com.accenture.microservice.microfarmserver.controller;

import com.accenture.microservice.microfarmserver.model.Chicken;
import com.accenture.microservice.microfarmserver.model.Farm;
import com.accenture.microservice.microfarmserver.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FarmController {

    @Autowired
    FarmService farmService;

    @Autowired
    RestTemplate restTemplate;

    //	Metodo para obtener todas las granjas
    @RequestMapping(value = "/farms", method = RequestMethod.GET)
    @ResponseBody
    public List<Farm>  getAllFarm(){
        List<Farm> lstFarms = farmService.getAllFarms();
        return lstFarms;
    }

    //	Metodo para obtener una granja espesifica
    @RequestMapping(value = "/farms/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Farm getFarm(@PathVariable("id") Integer id){
        return farmService.getFarm(id);
    }

    @RequestMapping(value = "/farms/farm/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFarmWithChickens(@PathVariable("id") Integer id){
        Map<String, Object> getFarm = farmService.getFarmWithChickens(id);
        return getFarm;
    }

    @RequestMapping(value = "/farms/farm/{farmId}/chickens", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getChickens(@PathVariable("farmId") Integer farmId){
        Farm farm = farmService.getFarm(farmId);
        List<Integer> lstChicken = farm.getChicken_id();
        List<Object> chickens = new ArrayList<>();
        for(Integer chickenID : lstChicken){
            String url = "http://microservice-chicken-server/chickens/chicken/" + chickenID;
            Object chicken = restTemplate.getForObject(url, Object.class);
            chickens.add(chicken);
        }
        return chickens;
    }

    //	Metodo para guardar una granja
    @RequestMapping(value = "/farms/farm", method = RequestMethod.POST)
    public ResponseEntity<?> saveFarm(@RequestBody Farm farm){
        if(farmService.saveFarm(farm)) {
            return new ResponseEntity<>(farm, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "farms/chicken", method = RequestMethod.POST)
    public ResponseEntity<?> saveChicken(@RequestBody Chicken chicken){
        if (farmService.saveChicken(chicken)){
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //	Metodo para modificar una granja
    @RequestMapping(value = "/farms/{id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateFarm(@RequestBody Farm farm){
        if(farmService.updateFarm(farm)) {
            return new ResponseEntity<>(farm, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar una granja
    @RequestMapping(value = "/farms/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFarm(@PathVariable("id") int id){
        Farm farm = farmService.getFarm(id);
        if(farmService.deleteFarm(farm)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar el ID de una granja
    @RequestMapping(value = "farms/{id}/deleteId", method = RequestMethod.GET)
    public ResponseEntity<?> deleteIdFarm(@PathVariable("id") Integer id){
        if(farmService.deleteFarmId(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

}
