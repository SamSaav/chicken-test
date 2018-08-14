package com.accenture.microservice.microfarmserver.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Chicken> chickens;

    public Farm(){}

    public Farm(String name, List<Chicken> chickens){
        this.id = null;
        this.name = name;
        this.chickens = chickens;
    }

    public void addChicken(Integer id){
        Chicken chicken = new Chicken(id);
        chicken.setFarm(this);
        chickens.add(chicken);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chicken> getChickens() {
        return chickens;
    }

    public void setChickens(List<Chicken> chickens) {
        this.chickens = chickens;
    }

    public List<Integer> getChicken_id(){
        return this.getChickens().stream().map(Chicken::getId).collect(Collectors.toList());
    }
}
