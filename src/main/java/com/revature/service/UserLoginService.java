package com.revature.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class UserLoginService {

	private static Scanner scan = new Scanner(System.in);
	private static UserDatabaseSerialization userDBSerial = new UserDatabaseSerialization();
	private static Map<String, User> userDB = null;
	protected User.UserType userType = User.UserType.Generic;
	
	public User logDriverMain () {
		User userInfo;
		
		
		userDB = userDBSerial.deserializeDB(userType.toString());
		
		while (true) {
			printLoginMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				userInfo = getUserInfoInput();
				if (checkLoginInfo(userInfo)) {
					return getUserData(userInfo.getUsername());
				}
				break;
			case "2": 
				registerUser();
				break;
			case "3":
				return null;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
			
		}
	}
	
	public void printLoginMessage () {
		System.out.println("User Login");
	}
	
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Register");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	public User getUserInfoInput () {
		User userInput = new User ();
		System.out.println("Please Enter Your Username: ");
		userInput.setUsername(scan.nextLine());
		
		System.out.println("Please Enter Your Password: ");
		userInput.setPassword(scan.nextLine());

		return userInput;
	}
	
	public boolean checkLoginInfo (User loginInfo) {
		if (authenticateUser(loginInfo)) {
			System.out.println("Successful Login");
			return true;
		}
				
		System.out.println("Invalid Username/Password");
		
		return false;
	}
	
	public User registerUser () {		
		User newUser = new User();
		
		while (true) {
			System.out.println("Please enter a Username to use: ");
			
			newUser.setUsername(scan.nextLine());
			if (checkUserExists(newUser.getUsername())) {
				System.out.println("Username currently in use.");
				continue;
			}
			
			System.out.println("Please enter a Password to use: ");
			newUser.setPassword(scan.nextLine());
			break;
		}
		
		newUser.setOwnedCars(new LinkedList<Car>());
		setUserType(newUser);
		
		userDB.put(newUser.getUsername(), newUser);
		
		userDBSerial.serializeDB (userDB, newUser.getUserType().toString());
		
		return newUser;
	}
	
	public void setUserType (User newUser) {
		newUser.setUserType(User.UserType.Generic);
	}
	
	public boolean checkUserExists (String username) {
		if (userDB.get(username) != null)
			return true;
		else
			return false;
	}
	
	public User getUserData (String username) {
		if (checkUserExists(username))
			return userDB.get(username);
		
		return null;
	}
	
	public boolean authenticateUser (User authUser) {
		if (!checkUserExists(authUser.getUsername()))
			return false;
		
		User userHolder = userDB.get(authUser.getUsername());
		
		if (userHolder.getPassword().equals(authUser.getPassword()))
			return true;
		else
			return false;
	}
	

}
