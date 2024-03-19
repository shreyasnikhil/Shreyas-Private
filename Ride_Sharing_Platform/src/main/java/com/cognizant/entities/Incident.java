package com.cognizant.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "incident")
public class Incident {

	@Id
	private String incidentID;
	@Column(name = "incidentdata")
	private LocalDate incidentData;
	@Column(name = "reportdate")
	private LocalDate reportDate;
	@Column(name = "incidentreportedbyuserid")
	private int incidentReportedByUserID;
	// Foreign Key
	@ManyToOne
	@JoinColumn(name = "incidenttypeid")
	private IncidentTypes incidentTypes;
	@Column(name = "resolutioneta")
	private LocalDate resolutionETA;
	@Column(name = "investigatedbyuserid")
	private int investigatedByUserId;
	@Column(name = "incidentsummary")
	private String incidentSummary;
	@Column(name = "incidentdetails")
	private String incidentDetails;
	@Column(name = "bookingid")
	private int bookingId;
	@Column(name = "status")
	private String status;
	public String getIncidentID() {
		return incidentID;
	}
	public void setIncidentID(String incidentID) {
		this.incidentID = incidentID;
	}
	public LocalDate getIncidentData() {
		return incidentData;
	}
	public void setIncidentData(LocalDate incidentData) {
		this.incidentData = incidentData;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public int getIncidentReportedByUserID() {
		return incidentReportedByUserID;
	}
	public void setIncidentReportedByUserID(int incidentReportedByUserID) {
		this.incidentReportedByUserID = incidentReportedByUserID;
	}
	public IncidentTypes getIncidentTypes() {
		return incidentTypes;
	}
	public void setIncidentTypes(IncidentTypes incidentTypes) {
		this.incidentTypes = incidentTypes;
	}
	public LocalDate getResolutionETA() {
		return resolutionETA;
	}
	public void setResolutionETA(LocalDate resolutionETA) {
		this.resolutionETA = resolutionETA;
	}
	public int getInvestigatedByUserId() {
		return investigatedByUserId;
	}
	public void setInvestigatedByUserId(int investigatedByUserId) {
		this.investigatedByUserId = investigatedByUserId;
	}
	public String getIncidentSummary() {
		return incidentSummary;
	}
	public void setIncidentSummary(String incidentSummary) {
		this.incidentSummary = incidentSummary;
	}
	public String getIncidentDetails() {
		return incidentDetails;
	}
	public void setIncidentDetails(String incidentDetails) {
		this.incidentDetails = incidentDetails;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
