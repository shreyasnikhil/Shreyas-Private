package com.cognizant.models;

public class IncidentTypeDTO {

	private int id;

	private int type;

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

	public IncidentTypeDTO() {
		super();
	}
}
