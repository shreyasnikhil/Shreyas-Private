package com.cognizant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.entities.Incident;
import com.cognizant.entities.IncidentTypes;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, String> {
	List<Incident> findByIncidentTypes(IncidentTypes incidentTypes);

	List<Incident> findByStatus(String status);

	List<Incident> findByBookingId(int bookingId);
}
