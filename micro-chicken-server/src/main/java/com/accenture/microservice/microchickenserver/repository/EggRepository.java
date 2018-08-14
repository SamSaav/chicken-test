package com.accenture.microservice.microchickenserver.repository;

import com.accenture.microservice.microchickenserver.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggRepository extends JpaRepository<Egg, Integer> {
}
