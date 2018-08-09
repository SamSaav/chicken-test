package com.accenture.microservice.microeggserver.controller;

import com.accenture.microservice.microeggserver.model.Egg;
import com.accenture.microservice.microeggserver.service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EggController {

    @Autowired
    EggService eggService;

    //	Metodo para obtener todas las granjas
    @RequestMapping(value = "/eggs", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEgg(){
        List<Egg> lstEggs = eggService.getAllEggs();
        if (lstEggs != null && !lstEggs.isEmpty()) {
            return new ResponseEntity<>(lstEggs, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para obtener una granja espesifica
    @RequestMapping(value = "/eggs/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEgg(@PathVariable("id") int id){
        Egg egg = eggService.getEgg(id);
        if(egg != null) {
            return new ResponseEntity<>(egg, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para guardar una granja
    @RequestMapping(value = "/eggs/egg", method = RequestMethod.POST)
    public ResponseEntity<?> saveEgg(@RequestBody Egg egg){
        if(eggService.saveEgg(egg)) {
            return new ResponseEntity<>(egg, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para modificar una granja
    @RequestMapping(value = "/eggs/{id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateEgg(@RequestBody Egg egg){
        if(eggService.updateEgg(egg)) {
            return new ResponseEntity<>(egg, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar una granja
    @RequestMapping(value = "/egg/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEgg(@PathVariable("id") Integer id){
        Egg egg = eggService.getEgg(id);
        if(eggService.deleteEgg(egg)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

    //	Metodo para eliminar el ID de una granja
    @RequestMapping(value = "egg/{id}/deleteId", method = RequestMethod.GET)
    public ResponseEntity<?> deleteIdFarm(@PathVariable("id") Integer id){
        if(eggService.deleteEggId(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin contenido",HttpStatus.NO_CONTENT);
    }

}
