package com.accenture.microservice.microchickenserver.controller;

import com.accenture.microservice.microchickenserver.model.Chicken;
import com.accenture.microservice.microchickenserver.service.ChickenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChickenController {

    @Autowired
    ChickenService chickenService;

    //	Metodo para obtener todas las granjas
    @RequestMapping(value = "/chickens", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEggs(){
        List<Chicken> lstChickens = chickenService.getAllChickens();
        if (lstChickens != null && !lstChickens.isEmpty()) {
            return new ResponseEntity<>(lstChickens, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para obtener una granja espesifica
    @RequestMapping(value = "/chickens/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getChicken(@PathVariable("id") Integer id){
        Chicken chicken = chickenService.getChicken(id);
        if(chicken != null) {
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para guardar una granja
    @RequestMapping(value = "/chickens/chicken", method = RequestMethod.POST)
    public ResponseEntity<?> saveChicken(@RequestBody Chicken chicken){
        if(chickenService.saveChicken(chicken)) {
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para modificar una granja
    @RequestMapping(value = "/chickens/{id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateChicken(@RequestBody Chicken chicken){
        if(chickenService.updateChicken(chicken)) {
            return new ResponseEntity<>(chicken, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar una granja
    @RequestMapping(value = "/chickens/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteChicken(@PathVariable("id") Integer id){
        Chicken chicken = chickenService.getChicken(id);
        if(chickenService.deleteChicken(chicken)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar el ID de una granja
    @RequestMapping(value = "chickens/{id}/deleteId", method = RequestMethod.GET)
    public ResponseEntity<?> deleteIdChicken(@PathVariable("id") Integer id){
        if(chickenService.deleteChickenId(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

}
