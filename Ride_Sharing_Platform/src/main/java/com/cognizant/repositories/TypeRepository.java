package com.cognizant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.entities.IncidentTypes;

public interface TypeRepository extends JpaRepository<IncidentTypes, Integer> {

}
