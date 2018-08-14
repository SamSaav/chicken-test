package com.accenture.microservice.microchickenserver.model;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Chicken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer farm_id;

    @OneToMany(mappedBy = "chicken", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Egg> eggs;

    public Chicken() {
    }

    public Chicken(Integer number, Integer farm_id) {
        this.id = null;
        this.number = number;
        this.farm_id = farm_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(Integer farm_id) {
        this.farm_id = farm_id;
    }

    public List<Egg> getEggs() {
        return eggs;
    }

    public void setEggs(List<Egg> eggs) {
        this.eggs = eggs;
    }

    public List<Integer> getEggs_id(){
        return this.getEggs().stream().map(Egg::getId).collect(Collectors.toList());
    }
}
