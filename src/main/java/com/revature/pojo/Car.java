package com.revature.pojo;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable{

	private int id;
	
	private String make;
	private String model;
	private String year;
	
	private List<Float> remPayments;	
	
	public List<Float> getRemPayments() {
		return remPayments;
	}

	public void setRemPayments(List<Float> remPayments) {
		this.remPayments = remPayments;
	}

	public Car () {
		
	}
	
	public Car(int id, String make, String model, String year) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
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
	public int getId() {
		return id;
	}
	
	
	public String getCarInfo() {
		return year + " " + make + " " + model;
	}
	
}
