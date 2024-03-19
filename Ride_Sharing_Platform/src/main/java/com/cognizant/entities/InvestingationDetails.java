package com.cognizant.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "investigationdetails")
public class InvestingationDetails {
	@Id
	private int id;
	@Column(name = "findings")
	private String findings;
	@Column(name = "suggestions")
	private String suggestions;
	@Column(name = "investigationdate")
	private LocalDate investigationDate;
//	foreign key (IncidentsIncidentID) references Incident(IncidentID)
	@ManyToOne
	@JoinColumn(name = "incidentsincidentid")
	private Incident incident;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFindings() {
		return findings;
	}
	public void setFindings(String findings) {
		this.findings = findings;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	public LocalDate getInvestigationDate() {
		return investigationDate;
	}
	public void setInvestigationDate(LocalDate investigationDate) {
		this.investigationDate = investigationDate;
	}
	public Incident getIncident() {
		return incident;
	}
	public void setIncident(Incident incident) {
		this.incident = incident;
	}
	

}
