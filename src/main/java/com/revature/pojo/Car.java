package com.revature.pojo;

import java.io.Serializable;

public class Car implements Serializable{

	private String make;
	private String model;
	private String year;
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCarInfo() {
		return year + " " + make + " " + model;
	}
	
}
