package com.accenture.microservice.microchickenserver.service;

import com.accenture.microservice.microchickenserver.model.Chicken;
import com.accenture.microservice.microchickenserver.repository.ChickenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChickenService {

    @Autowired
    ChickenRepository chickenRepository;

    public List<Chicken> getAllChickens(){
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())){
            return lstChickens;
        }else return new ArrayList<>();
    }

    public Chicken getChicken(Integer id){
        Chicken chicken = chickenRepository.getById(id);
        if (chicken != null){
            return chicken;
        }else return null;
    }

    public boolean saveChicken(Chicken chicken){
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())){
            chicken.setId(lstChickens.size() + 1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        }else if(lstChickens.isEmpty()){
            chicken.setId(1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        }else return false;
    }

    public boolean updateChicken(Chicken chicken){
        List<Chicken> lstChickens = chickenRepository.findAll();
        boolean tORf = false;
        if(!lstChickens.isEmpty()) {
            for(Chicken c : lstChickens) {
                if(c.getId() == chicken.getId()) {
                    if(chicken.getNumber() != null) c.setNumber(chicken.getNumber());
                    chickenRepository.save(c);
                    tORf = true;
                }
            }
            return tORf;
        }else return false;
    }

    public boolean deleteChicken(Chicken chicken){
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())){
            lstChickens.remove(chicken);
            chickenRepository.delete(chicken);
            return true;
        }else return false;
    }

    public boolean deleteChickenId(Integer id){
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (lstChickens.isEmpty()){
            return false;
        }else{
            lstChickens.remove(id - 1);
            chickenRepository.deleteById(id - 1);
            return true;
        }
    }

}
