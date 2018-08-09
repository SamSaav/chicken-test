package com.accenture.microservice.microchickenserver.repository;

import com.accenture.microservice.microchickenserver.model.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Integer> {

    Chicken getById(Integer id);

}
