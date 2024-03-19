package com.cognizant.models;

import java.time.LocalDate;



public class InvestigationReportDTO {
	
	
	private int id;

	private String Findings;

	private String Suggestions;

	private LocalDate InvestigationDate;

	private int incidentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFindings() {
		return Findings;
	}

	public void setFindings(String findings) {
		Findings = findings;
	}

	public String getSuggestions() {
		return Suggestions;
	}

	public void setSuggestions(String suggestions) {
		Suggestions = suggestions;
	}

	public LocalDate getInvestigationDate() {
		return InvestigationDate;
	}

	public void setInvestigationDate(LocalDate investigationDate) {
		InvestigationDate = investigationDate;
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}



	
}
