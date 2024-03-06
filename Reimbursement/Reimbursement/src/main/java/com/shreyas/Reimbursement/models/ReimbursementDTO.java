package com.shreyas.Reimbursement.models;

import java.time.LocalDate;
public class ReimbursementDTO {
	private int id;
	private int travelrequestid;
	private int requestRaisedByEmployeeId;
	private LocalDate requestDate;
	private int reimbursementTypesId;
	private String invoiceNo;
	private LocalDate invoiceDate;
	private int invoiceAmount;
	private String documentUrl;
	private LocalDate requestProcessedOn;
	private int requestProcessedByEmployeeId;
	private String status;
	private String remarks;
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

//	public ReimbursementTypesDTO getReimbursementTypes() {
//		return reimbursementTypes;
//	}
//
//	public void setReimbursementTypes(ReimbursementTypesDTO reimbursementTypes) {
//		this.reimbursementTypes = reimbursementTypes;
//	}

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

//	public String getStatus() {
//		return status.name();
//	}
//	public void setStatus(String status) throws IllegalArgumentException {
//		this.status = Status.valueOf(status);
//	}
	
	public String getRemarks() {
		return remarks;
	}

	public int getReimbursementTypesId() {
		return reimbursementTypesId;
	}

	public void setReimbursementTypesId(int reimbursementTypesId) {
		this.reimbursementTypesId = reimbursementTypesId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
