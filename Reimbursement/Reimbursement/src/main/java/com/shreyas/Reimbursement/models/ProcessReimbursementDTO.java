package com.shreyas.Reimbursement.models;

import java.time.LocalDate;

import com.shreyas.Reimbursement.validators.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;

public class ProcessReimbursementDTO {
	private int reimbursementId;
	private int requestRaisedByEmployeeId;
	@FutureOrPresent
	private LocalDate requestProcessedOn;
	private int requestProcessedByEmployeeId;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String remarks;
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
		return status.name();
	}
	public void setStatus(String status) throws IllegalArgumentException {
		this.status = Status.valueOf(status);
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getRequestRaisedByEmployeeId() {
		return requestRaisedByEmployeeId;
	}
	public void setRequestRaisedByEmployeeId(int requestRaisedByEmployeeId) {
		this.requestRaisedByEmployeeId = requestRaisedByEmployeeId;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public ProcessReimbursementDTO() {
		super();
		requestProcessedOn=LocalDate.now();
	} 
	
}
