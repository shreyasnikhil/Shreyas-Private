package com.shreyas.Reimbursement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name="types")
public class ReimbursementTypes {
	@Id
	 private int id;
	@Column(name="type")
	 private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ReimbursementTypes() {
		super();
	}
}
