package com.cognizant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.models.IncidentTypeDTO;
import com.cognizant.models.IncidentsDTO;
import com.cognizant.models.InvestigationReportDTO;
import com.cognizant.services.IncidentManagementServices;

import jakarta.validation.Valid;
@RestController
@RequestMapping("api")
public class RideController {
	@Autowired
	IncidentManagementServices incidentManagementServices;

	// Endpoint1
	@GetMapping("incidents/types")
	public ResponseEntity<?> handleGetAllIncidentTypes() {
		List<IncidentTypeDTO> responseList = incidentManagementServices.getAllIncidentTypes();
		// int c=1/0;
		ResponseEntity<List<IncidentTypeDTO>> responseEntity = null;
		if (!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<IncidentTypeDTO>>(responseList, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	// Endpoint 2
	@PostMapping("incidents/report")
	public ResponseEntity<?> persistIncident(@Valid@RequestBody IncidentsDTO dto) {
		String result = incidentManagementServices.reportIncident(dto);
		if (result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Endpoint 3
	@GetMapping("incidents")
	public ResponseEntity<?> handleGetAllIncidents() {
		List<IncidentsDTO> responseList = incidentManagementServices.getAllIncidents();
		// int c=1/0;
		ResponseEntity<List<IncidentsDTO>> responseEntity = null;
		if (!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<IncidentsDTO>>(responseList, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	// Endpoint 4
	@GetMapping("incidents/{id}")
	public ResponseEntity<?> handleGetIncidentsByPathParam(@PathVariable("id") String id) {
		IncidentsDTO dto = incidentManagementServices.getIncidentById(id);
		ResponseEntity<IncidentsDTO> responseEntity = null;
		if (dto.getIncidentID() != null) {
			responseEntity = new ResponseEntity<IncidentsDTO>(dto, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	// Endpoint 5
	@PutMapping("incidents/{id}/investigationreport")
	public ResponseEntity<?> handleUpdateEmployeeSalary(@PathVariable("id") int id,@Valid@RequestBody InvestigationReportDTO dto){
		String result=incidentManagementServices.updateIncidentReport(id,dto);
		if(result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
}
