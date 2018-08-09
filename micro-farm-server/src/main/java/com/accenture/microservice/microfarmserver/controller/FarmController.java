package com.accenture.microservice.microfarmserver.controller;

import com.accenture.microservice.microfarmserver.model.Farm;
import com.accenture.microservice.microfarmserver.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FarmController {

    @Autowired
    FarmService farmService;

    //	Metodo para obtener todas las granjas
    @RequestMapping(value = "/farms", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFarm(){
        List<Farm> lstFarms = farmService.getAllFarms();
        if (lstFarms != null && !lstFarms.isEmpty()) {
            return new ResponseEntity<>(lstFarms, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para obtener una granja espesifica
    @RequestMapping(value = "/farms/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFarm(@PathVariable("id") int id){
        Farm farm = farmService.getFarm(id);
        if(farm != null) {
            return new ResponseEntity<>(farm, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para guardar una granja
    @RequestMapping(value = "/farms/farm", method = RequestMethod.POST)
    public ResponseEntity<?> saveFarm(@RequestBody Farm farm){
        if(farmService.saveFarm(farm)) {
            return new ResponseEntity<>(farm, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
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
