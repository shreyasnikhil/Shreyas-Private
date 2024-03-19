package com.cognizant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.entities.InvestingationDetails;

public interface InvestigationRepository extends JpaRepository<InvestingationDetails, Integer> {

}
