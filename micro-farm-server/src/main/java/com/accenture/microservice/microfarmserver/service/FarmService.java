package com.accenture.microservice.microfarmserver.service;

import com.accenture.microservice.microfarmserver.model.Farm;
import com.accenture.microservice.microfarmserver.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmService {

    @Autowired
    FarmRepository farmRepository;

    public List<Farm> getAllFarms(){
        List<Farm> lstFarms = farmRepository.findAll();
        if (!(lstFarms.isEmpty())){
            return lstFarms;
        }else return new ArrayList<>();
    }

    public Farm getFarm(Integer id){
        Farm farm = farmRepository.getById(id);
        if (farm != null){
            return farm;
        }else return null;
    }

    public boolean saveFarm(Farm farm){
        List<Farm> lstFarms = farmRepository.findAll();
        if (!(lstFarms.isEmpty())){
            farm.setId(lstFarms.size() + 1);
            lstFarms.add(farmRepository.save(farm));
            return true;
        }else if(lstFarms.isEmpty()){
            farm.setId(1);
            lstFarms.add(farmRepository.save(farm));
            return true;
        }else return false;
    }

    public boolean updateFarm(Farm farm){
        List<Farm> lstFarms = farmRepository.findAll();
        boolean tORf = false;
        if(!lstFarms.isEmpty()) {
            for(Farm f : lstFarms) {
                if(f.getId() == farm.getId()) {
                    if(farm.getName() != null) f.setName(farm.getName());
                    farmRepository.save(f);
                    tORf = true;
                }
            }
            return tORf;
        }else return false;
    }

    public boolean deleteFarm(Farm farm){
        List<Farm> lstFarms = farmRepository.findAll();
        if (!(lstFarms.isEmpty())){
            lstFarms.remove(farm);
            farmRepository.delete(farm);
            return true;
        }else return false;
    }

    public boolean deleteFarmId(Integer id){
        List<Farm> lstFarms = farmRepository.findAll();
        if (lstFarms.isEmpty()){
            return false;
        }else{
            lstFarms.remove(id - 1);
            farmRepository.deleteById(id - 1);
            return true;
        }
    }

}
