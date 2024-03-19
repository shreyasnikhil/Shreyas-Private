package com.cognizant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "incidenttypes")
public class IncidentTypes {

	@Id
	private int id;

	@Column(name = "type")
	private int type;
	@Column(name = "expectedslaindays")
	private int ExpectedSLAInDay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getExpectedSLAInDay() {
		return ExpectedSLAInDay;
	}

	public void setExpectedSLAInDay(int expectedSLAInDay) {
		ExpectedSLAInDay = expectedSLAInDay;
	}

	public IncidentTypes() {
		super();
	}

}
