package com.cognizant.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.entities.Incident;
import com.cognizant.entities.IncidentTypes;
import com.cognizant.entities.InvestingationDetails;
import com.cognizant.models.IncidentTypeDTO;
import com.cognizant.models.IncidentsDTO;
import com.cognizant.models.InvestigationReportDTO;
import com.cognizant.models.IncidentsDTO;
import com.cognizant.repositories.IncidentRepository;
import com.cognizant.repositories.InvestigationRepository;
import com.cognizant.repositories.TypeRepository;

@Service
public class IncidentManagementServices {

	@Autowired
	private IncidentRepository incidentRepository;
	@Autowired
	private InvestigationRepository investigationRepository;
	@Autowired
	private TypeRepository typeRepository;

	// Endpoint 1
	public List<IncidentTypeDTO> getAllIncidentTypes() {
		List<IncidentTypeDTO> ans = new ArrayList<>();
		List<IncidentTypes> list = typeRepository.findAll();
		for (IncidentTypes types : list) {
			IncidentTypeDTO dto = new IncidentTypeDTO();
			dto.setExpectedSLAInDay(types.getExpectedSLAInDay());
			dto.setId(types.getId());
			dto.setType(types.getType());
			ans.add(dto);
		}
		return ans;
	}

	// Endpoint 2
	public String reportIncident(IncidentsDTO dto) {
		Period period = Period.between(dto.getIncidentData(), dto.getReportDate());
		if(period.getDays() > 2){
		    throw new IllegalArgumentException("Report Date should be with in two days of the incident date.");
		}
		
		if(incidentRepository.findByBookingId(dto.getBookingID()).size()>=1){
		    throw new IllegalArgumentException("An incident with same Booking Id already exists");
		}
		
		Incident incident = new Incident();
		incident.setBookingId(dto.getBookingID());
		incident.setIncidentData(dto.getIncidentData());
		incident.setIncidentDetails(dto.getIncidentDetails());
		incident.setIncidentID(dto.getIncidentID());
		incident.setIncidentReportedByUserID(dto.getIncidentReportedByUserID());
		incident.setIncidentSummary(dto.getIncidentSummary());
		IncidentTypes incidentTypes = new IncidentTypes();
		incidentTypes.setId(dto.getIncidentTypeId());
		incident.setIncidentTypes(incidentTypes);
		incident.setInvestigatedByUserId(dto.getInvestigatedByUserId());
		incident.setReportDate(dto.getReportDate());
		incident.setResolutionETA(dto.getResolutionETA());
		incident.setStatus(dto.getStatus());
		if (incidentRepository.save(incident) != null) {
			return "success";
		}

		return "fail";
	}

	// Endpoint 3
	public List<IncidentsDTO> getAllIncidents() {
		List<IncidentsDTO> ans = new ArrayList<>();
		List<Incident> list = incidentRepository.findAll();
		for (Incident incident : list) {
			IncidentsDTO dto = new IncidentsDTO();
			dto.setBookingID(incident.getBookingId());
			dto.setIncidentData(incident.getIncidentData());
			dto.setIncidentDetails(incident.getIncidentDetails());
			dto.setIncidentID(incident.getIncidentID());
			dto.setIncidentReportedByUserID(incident.getIncidentReportedByUserID());
			dto.setIncidentSummary(incident.getIncidentSummary());
			dto.setIncidentTypeId(incident.getIncidentTypes().getId());
			dto.setInvestigatedByUserId(incident.getInvestigatedByUserId());
			dto.setReportDate(incident.getReportDate());
			dto.setResolutionETA(incident.getResolutionETA());
			dto.setStatus(incident.getStatus());
			ans.add(dto);
		}
		return ans;
	}

	// Endpoint 4
	public IncidentsDTO getIncidentById(String id) {
		Optional<Incident> optional = incidentRepository.findById(id);
		IncidentsDTO dto = new IncidentsDTO();
		if (optional.isPresent()) {
			Incident incident = optional.get();
			dto.setBookingID(incident.getBookingId());
			dto.setIncidentData(incident.getIncidentData());
			dto.setIncidentDetails(incident.getIncidentDetails());
			dto.setIncidentID(incident.getIncidentID());
			dto.setIncidentReportedByUserID(incident.getIncidentReportedByUserID());
			dto.setIncidentSummary(incident.getIncidentSummary());
			dto.setIncidentTypeId(incident.getIncidentTypes().getId());
			dto.setInvestigatedByUserId(incident.getInvestigatedByUserId());
			dto.setReportDate(incident.getReportDate());
			dto.setResolutionETA(incident.getResolutionETA());
			dto.setStatus(incident.getStatus());
		}
		return dto;
	}
	//Endpoint 5
	public String updateIncidentReport(int incidentId,InvestigationReportDTO dto) {
		Optional<InvestingationDetails> optional=investigationRepository.findById(incidentId);
		if(optional.isPresent()) {
			InvestingationDetails details=optional.get();
			details.setFindings(dto.getFindings());
			details.setSuggestions(dto.getSuggestions());
			details.setInvestigationDate(dto.getInvestigationDate());
			if(investigationRepository.save(details)!=null) {
				return "success";
			}
		}
		return "fail";
	}
	public List<Incident> calculateETA(IncidentTypeDTO dto) {
		Optional<IncidentTypes> optional=typeRepository.findById(dto.getId());
	    List<Incident> list=incidentRepository.findByIncidentTypes(optional.get());
	    for(Incident incident:list) {
	    	incident.setResolutionETA(incident.getReportDate().plusDays(dto.getExpectedSLAInDay()));
	    	incidentRepository.save(incident);
	    }
		return list;
	}
	public List<IncidentsDTO> getPendingRequests() {
		List<Incident> incidents=incidentRepository.findByStatus("Pending");
		List<IncidentsDTO> ans=new ArrayList<>();
		for(Incident incident:incidents) {
			IncidentsDTO dto = new IncidentsDTO();
			dto.setBookingID(incident.getBookingId());
			dto.setIncidentData(incident.getIncidentData());
			dto.setIncidentDetails(incident.getIncidentDetails());
			dto.setIncidentID(incident.getIncidentID());
			dto.setIncidentReportedByUserID(incident.getIncidentReportedByUserID());
			dto.setIncidentSummary(incident.getIncidentSummary());
			dto.setIncidentTypeId(incident.getIncidentTypes().getId());
			dto.setInvestigatedByUserId(incident.getInvestigatedByUserId());
			dto.setReportDate(incident.getReportDate());
			dto.setResolutionETA(incident.getResolutionETA());
			dto.setStatus(incident.getStatus());
			ans.add(dto);
		}
		return ans;
	}
	public String createCustomIncidentId(LocalDate incidentDate) {
        String customIncidentId = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        String getDate = dateTimeFormatter.format(incidentDate);
        String currYear = getDate.split("-")[2];
        customIncidentId += currYear;
 
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long uniqueNumber = (int) (Math.abs(leastSignificantBits) % 9000) + 1000;
 
        customIncidentId += "-";
        customIncidentId += uniqueNumber;
        return customIncidentId;
    }

}





