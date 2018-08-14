package com.accenture.microservice.microchickenserver.service;

import com.accenture.microservice.microchickenserver.model.Chicken;
import com.accenture.microservice.microchickenserver.repository.ChickenRepository;
import com.accenture.microservice.microchickenserver.repository.EggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChickenService {

    @Autowired
    ChickenRepository chickenRepository;

    @Autowired
    EggRepository eggRepository;

    @LoadBalanced
    @Bean
    public RestTemplate RestTemplate (RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    public List<Chicken> getAllChickens() {
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())) {
            return lstChickens;
        } else return new ArrayList<>();
    }

    public Chicken getChicken(Integer id) {
        Chicken chicken = chickenRepository.getById(id);
        if (chicken != null) {
            return chicken;
        } else return null;
    }

    public Map<String, Object> getChickeWithEggs(Integer id){
        String url = "http://microservice-chicken-server/chickens/chicken/"+id+"/eggs";
        Chicken chicken = getChicken(id);
        Map<String, Object> chickenEggs = new LinkedHashMap<>();
        chickenEggs.put("id", chicken.getId());
        chickenEggs.put("number", chicken.getNumber());
        chickenEggs.put("eggs", restTemplate.getForObject(url, Object.class));
        return chickenEggs;
    }

    public boolean saveChicken(Chicken chicken) {
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())) {
            chicken.setId(lstChickens.size() + 1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        } else if (lstChickens.isEmpty()) {
            chicken.setId(1);
            lstChickens.add(chickenRepository.save(chicken));
            return true;
        } else return false;
    }

    public boolean updateChicken(Chicken chicken) {
        List<Chicken> lstChickens = chickenRepository.findAll();
        boolean tORf = false;
        if (!lstChickens.isEmpty()) {
            for (Chicken c : lstChickens) {
                if (c.getId() == chicken.getId()) {
                    if (chicken.getNumber() != null) c.setNumber(chicken.getNumber());
                    if (chicken.getFarm_id() != null) c.setFarm_id(chicken.getFarm_id());
                    chickenRepository.save(c);
                    tORf = true;
                }
            }
            return tORf;
        } else return false;
    }

    public boolean deleteChicken(Chicken chicken) {
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (!(lstChickens.isEmpty())) {
            lstChickens.remove(chicken);
            chickenRepository.delete(chicken);
            return true;
        } else return false;
    }

    public boolean deleteChickenId(Integer id) {
        List<Chicken> lstChickens = chickenRepository.findAll();
        if (lstChickens.isEmpty()) {
            return false;
        } else {
            lstChickens.remove(id - 1);
            chickenRepository.deleteById(id - 1);
            return true;
        }
    }

}
