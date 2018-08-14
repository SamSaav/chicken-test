package com.accenture.microservice.microfarmserver.service;

import com.accenture.microservice.microfarmserver.model.Chicken;
import com.accenture.microservice.microfarmserver.model.Farm;
import com.accenture.microservice.microfarmserver.repository.ChickenRepository;
import com.accenture.microservice.microfarmserver.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FarmService {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    ChickenRepository chickenRepository;

    @LoadBalanced
    @Bean
    public RestTemplate RestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    public List<Farm> getAllFarms(){
        List<Farm> lstFarms = farmRepository.findAll();
        return lstFarms;
    }

    public Farm getFarm(Integer id){
        Farm farm = farmRepository.getById(id);
        if (farm != null){
            return farm;
        }else return null;
    }

    public Map<String, Object> getFarmWithChickens(Integer id){
        String url = "http://microservice-farm-server/farms/farm/"+id+"/chickens";
        Farm farm = getFarm(id);
        Map<String, Object> farmChickens = new LinkedHashMap<>();
        farmChickens.put("id", farm.getId());
        farmChickens.put("name", farm.getName());
        farmChickens.put("chickens", restTemplate.getForObject(url, Object.class));
        return farmChickens;
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

    public boolean saveChicken(Chicken chicken){
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())){
            chicken.setId(lstChickens.size() + 1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        }else{
            chicken.setId(1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        }
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
