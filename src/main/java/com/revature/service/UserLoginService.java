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
	private static UserDatabaseSerialization userDB = new UserDatabaseSerialization();
	protected User.UserType userType = User.UserType.Generic;
	
	public User logDriverMain () {
		User userInfo;
		
		setUserType();
		userDB.deserializeDB(userType);
		
		while (true) {
			System.out.println("\n||=============================================||");
			printLoginMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				userInfo = getUserInfoInput();
				if (checkLoginInfo(userInfo)) {
					return userDB.getUser(userInfo.getUsername());
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
		
		System.out.println("\n||---------------------------------------------||");
		
		System.out.println("Please Enter Your Username: ");
		userInput.setUsername(scan.nextLine());
		
		System.out.println("Please Enter Your Password: ");
		userInput.setPassword(scan.nextLine());

		return userInput;
	}
	
	public boolean checkLoginInfo (User loginInfo) {
		if (authenticateUser(loginInfo)) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Successful Login");
			return true;
		}
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Invalid Username/Password");
		
		return false;
	}
	
	public User registerUser () {		
		String username = "";
		String password = "";
		
		while (true) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please enter a Username to use: ");
			
			username = scan.nextLine();
			if (userDB.checkUserExists(username)) {
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Username currently in use.");
				continue;
			}
			
			System.out.println("Please enter a Password to use: ");
			password = scan.nextLine();
			
			userDB.addUser(username, password);
			userDB.serializeDB();
			
			break;
		}	
		
		return null;
	}
	
	public void setUserType () {
		userType = User.UserType.Generic;
	}
	
	public boolean authenticateUser (User authUser) {
		if (!userDB.checkUserExists(authUser.getUsername()))
			return false;
		
		User userHolder = userDB.getUser(authUser.getUsername());
		
		if (userHolder.getPassword().equals(authUser.getPassword()))
			return true;
		else
			return false;
	}
	

}
