package com.accenture.microservice.microeggserver.service;

import com.accenture.microservice.microeggserver.model.Egg;
import com.accenture.microservice.microeggserver.repository.EggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EggService {

    @Autowired
    EggRepository eggRepository;

    public List<Egg> getAllEggs(){
        List<Egg> lstEggs = eggRepository.findAll();
        if (!(lstEggs.isEmpty())){
            return lstEggs;
        }else return new ArrayList<>();
    }

    public Egg getEgg(Integer id){
        Egg egg = eggRepository.getById(id);
        if (egg != null){
            return egg;
        }else return null;
    }

    public Map<String, Object> getEggDTO(Integer id){
        Egg egg = eggRepository.getById(id);
        Map<String, Object> getTheEgg = new LinkedHashMap<>();
        getTheEgg.put("id", egg.getId());
        getTheEgg.put("number", egg.getNumber());
        if (egg != null){
            return getTheEgg;
        }else return null;
    }

    public boolean saveEgg(Egg egg){
        List<Egg> lstEggs = eggRepository.findAll();
        if (!(lstEggs.isEmpty())){
            egg.setId(lstEggs.size() + 1);
            lstEggs.add(eggRepository.save(egg));
            return true;
        }else if(lstEggs.isEmpty()){
            egg.setId(1);
            lstEggs.add(eggRepository.save(egg));
            return true;
        }else return false;
    }

    public boolean updateEgg(Egg egg){
        List<Egg> lstEggs = eggRepository.findAll();
        boolean tORf = false;
        if(!lstEggs.isEmpty()) {
            for(Egg e : lstEggs) {
                if(e.getId() == egg.getId()) {
                    if(egg.getNumber() != null) e.setNumber(egg.getNumber());
                    if(egg.getChicken() != null) e.setChicken(egg.getChicken());
                    eggRepository.save(e);
                    tORf = true;
                }
            }
            return tORf;
        }else return false;
    }

    public boolean deleteEgg(Egg egg){
        List<Egg> lstEggs = eggRepository.findAll();
        if (!(lstEggs.isEmpty())){
            lstEggs.remove(egg);
            eggRepository.delete(egg);
            return true;
        }else return false;
    }

    public boolean deleteEggId(Integer id){
        List<Egg> lstEggs = eggRepository.findAll();
        if (lstEggs.isEmpty()){
            return false;
        }else{
            lstEggs.remove(id - 1);
            eggRepository.deleteById(id - 1);
            return true;
        }
    }

}
