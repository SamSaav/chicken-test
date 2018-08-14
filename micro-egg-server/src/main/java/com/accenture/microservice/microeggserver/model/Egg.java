package com.accenture.microservice.microeggserver.model;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Egg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer chicken;

    public Egg(){}

    public Egg(Integer number, Integer chicken){
        this.id = null;
        this.number = number;
        this.chicken = chicken;
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

    public Integer getChicken() {
        return chicken;
    }

    public void setChicken(Integer chicken) {
        this.chicken = chicken;
    }
}
