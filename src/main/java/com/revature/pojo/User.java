package com.revature.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	public enum UserType {
		Generic,
		Customer,
		Employee
	}
	
	private String username;
	private String password;
	
	private UserType userType;

	private List<Car> ownedCars;
	
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public List<Car> getOwnedCars() {
		return ownedCars;
	}
	public void setOwnedCars(List<Car> ownedCars) {
		this.ownedCars = ownedCars;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
