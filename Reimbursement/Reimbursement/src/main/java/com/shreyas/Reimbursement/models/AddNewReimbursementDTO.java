package com.shreyas.Reimbursement.models;

import java.time.LocalDate;

import com.shreyas.Reimbursement.validators.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.PastOrPresent;
public class AddNewReimbursementDTO {
	private int travelrequestid;
	private int requestRaisedByEmployeeId;
	private LocalDate requestDate;
	private int reimbursementTypesId;
	private String invoiceNo;
	@PastOrPresent
	private LocalDate invoiceDate;
	private int invoiceAmount;
	private String documentUrl;
	@Enumerated(EnumType.STRING)
	private Status status;

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

	public String getStatus() {
		return status.name();
	}

	public void setStatus(String status) throws IllegalArgumentException {
		this.status = Status.valueOf(status);
	}

	public AddNewReimbursementDTO() {
		super();
		requestDate = LocalDate.now();
		status = Status.valueOf("New");
	}

	public int getReimbursementTypesId() {
		return reimbursementTypesId;
	}

	public void setReimbursementTypesId(int reimbursementTypesId) {
		this.reimbursementTypesId = reimbursementTypesId;
	}
	

}
