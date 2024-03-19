package com.cognizant.models;

import java.time.LocalDate;

public class IncidentsDTO {

	private String IncidentID;

	private LocalDate IncidentData;

	private LocalDate ReportDate;

	private int IncidentReportedByUserID;

	private int incidentTypeId;

	private LocalDate ResolutionETA;

	private int InvestigatedByUserId;

	private String IncidentSummary;

	private String IncidentDetails;

	private int BookingID;

	private String Status;

	public String getIncidentID() {
		return IncidentID;
	}

	public void setIncidentID(String incidentID) {
		IncidentID = incidentID;
	}

	public LocalDate getIncidentData() {
		return IncidentData;
	}

	public void setIncidentData(LocalDate incidentData) {
		IncidentData = incidentData;
	}

	public LocalDate getReportDate() {
		return ReportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		ReportDate = reportDate;
	}

	public int getIncidentReportedByUserID() {
		return IncidentReportedByUserID;
	}

	public void setIncidentReportedByUserID(int incidentReportedByUserID) {
		IncidentReportedByUserID = incidentReportedByUserID;
	}

	public int getIncidentTypeId() {
		return incidentTypeId;
	}

	public void setIncidentTypeId(int incidentTypeId) {
		this.incidentTypeId = incidentTypeId;
	}

	public LocalDate getResolutionETA() {
		return ResolutionETA;
	}

	public void setResolutionETA(LocalDate resolutionETA) {
		ResolutionETA = resolutionETA;
	}

	public int getInvestigatedByUserId() {
		return InvestigatedByUserId;
	}

	public void setInvestigatedByUserId(int investigatedByUserId) {
		InvestigatedByUserId = investigatedByUserId;
	}

	public String getIncidentSummary() {
		return IncidentSummary;
	}

	public void setIncidentSummary(String incidentSummary) {
		IncidentSummary = incidentSummary;
	}

	public String getIncidentDetails() {
		return IncidentDetails;
	}

	public void setIncidentDetails(String incidentDetails) {
		IncidentDetails = incidentDetails;
	}

	public int getBookingID() {
		return BookingID;
	}

	public void setBookingID(int bookingID) {
		BookingID = bookingID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
