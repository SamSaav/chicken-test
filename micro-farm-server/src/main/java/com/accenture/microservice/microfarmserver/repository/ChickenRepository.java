package com.accenture.microservice.microfarmserver.repository;

import com.accenture.microservice.microfarmserver.model.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
}
