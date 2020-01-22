package com.revature.pojo;

import java.io.Serializable;
import java.util.List;

import com.revature.pojo.User;

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
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
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
	
	public User.UserType getUserTypeByInt(int i) {
		switch (i) {
		case 1:
			return User.UserType.Customer;
		case 2:
			return User.UserType.Employee;
		default:
			return null;
		}
	}
	
}
