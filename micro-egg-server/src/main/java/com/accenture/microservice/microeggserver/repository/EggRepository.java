package com.accenture.microservice.microeggserver.repository;

import com.accenture.microservice.microeggserver.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggRepository extends JpaRepository<Egg, Integer> {

    Egg getById(Integer id);

}
