package com.shreyas.Reimbursement.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "requests")
public class ReimbursementRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "travelrequestid")
	private int travelrequestid;

	@Column(name = "requestraisedbyemployeeid")
	private int requestRaisedByEmployeeId;
	@Column(name = "requestdate")
	private LocalDate requestDate;
	@ManyToOne
	@JoinColumn(name = "reimbursementtypeid")
	private ReimbursementTypes reimbursementTypes;
	@Column(name = "invoiceno")
	private String invoiceNo;
	@Column(name = "invoicedate")
	private LocalDate invoiceDate;
	@Column(name = "invoiceamount")
	private int invoiceAmount;
	@Column(name = "documenturl")
	private String documentUrl;
	@Column(name = "requestprocessedon")
	private LocalDate requestProcessedOn;
	@Column(name = "requestprocessedbyemployeeid")
	private int requestProcessedByEmployeeId;
	@Column(name = "status")
	private String status;
	@Column(name = "remarks")
	private String remarks;

	public int getRequestRaisedByEmployeeId() {
		return requestRaisedByEmployeeId;
	}

	public void setRequestRaisedByEmployeeId(int requestRaisedByEmployeeId) {
		this.requestRaisedByEmployeeId = requestRaisedByEmployeeId;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(int invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public LocalDate getRequestProcessedOn() {
		return requestProcessedOn;
	}

	public void setRequestProcessedOn(LocalDate requestProcessedOn) {
		this.requestProcessedOn = requestProcessedOn;
	}

	public int getRequestProcessedByEmployeeId() {
		return requestProcessedByEmployeeId;
	}

	public void setRequestProcessedByEmployeeId(int requestProcessedByEmployeeId) {
		this.requestProcessedByEmployeeId = requestProcessedByEmployeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTravelrequestid() {
		return travelrequestid;
	}

	public void setTravelrequestid(int travelrequestid) {
		this.travelrequestid = travelrequestid;
	}

	public ReimbursementTypes getReimbursementTypes() {
		return reimbursementTypes;
	}

	public void setReimbursementTypes(ReimbursementTypes reimbursementTypes) {
		this.reimbursementTypes = reimbursementTypes;
	}

}
