package com.accenture.microservice.microfarmserver.repository;

import com.accenture.microservice.microfarmserver.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {

    Farm getById(Integer id);

}
