package com.revature.service;

import java.util.Scanner;

import com.revature.dao.UserDAOPostgres;
import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.User;

public class UserLoginService {

	private static Scanner scan = new Scanner(System.in);
	private static UserDatabaseSerialization userDB = new UserDatabaseSerialization();
	//private static UserDAOPostgres userPos = new UserDAOPostgres();
	protected User.UserType userType = User.UserType.Generic;
	
	public User logDriverMain () {
		User userInfo;
		
		//Setup information required for operation
		setUserType();
		userDB.deserializeDB(userType);
		
		//Loop Menu for user to choose options
		while (true) {
			System.out.println("\n||=============================================||");
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			//User login
			case "1":
				userInfo = getUserInfoInput();
				if (authenticateUser(userInfo)) {
//return userPos.getByUsername(userInfo.getUsername());
					return userDB.getUser(userInfo.getUsername());
				}
				break;
			//Create new user
			case "2": 
				registerUser();
				break;
			//Exit
			case "3":
				return null;
			default:
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
			
		}
	}
	
//========================================================================
//				Methods to Override in children
//========================================================================
	//Message displayed before Options Menu
	public void printHeaderMessage () {
		System.out.println("User Login");
	}
	
	//Message displayed with all the choices the user will be able to do
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Register");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	//Called at start of logDriverMain to assign a User type to the Login Service
	public void setUserType () {
		userType = User.UserType.Generic;
	}
	
//========================================================================
//			Login Service Universal Methods
//========================================================================
	//Get username and password from user
	public User getUserInfoInput () {
		User userInput = new User ();
		
		System.out.println("\n||---------------------------------------------||");
		
		System.out.println("Please Enter Your Username: ");
		userInput.setUsername(scan.nextLine());
		
		System.out.println("Please Enter Your Password: ");
		userInput.setPassword(scan.nextLine());

		return userInput;
	}
	
	//Create a new user
	public void registerUser () {		
		String username = "";
		String password = "";
		
		while (true) {
			//Get username and password for new user
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please enter a Username to use: ");
			
			username = scan.nextLine();
			
			if (userDB.checkUserExists(username)) {
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Username currently in use. Please choose a new one.");
				continue;
			}
			
			System.out.println("Please enter a Password to use: ");
			password = scan.nextLine();
			
			//Assign username and password to new user
//userPos.add(new User(username, password, userType));
			
			userDB.createUser(username, password);
			userDB.serializeDB();
			
			break;
		}	
	}
	
	//Check if User's username and password match any user in the system
	public boolean authenticateUser (User authUser) {
		//Check if user exists
		if (userDB.checkUserExists(authUser.getUsername())) {
			
			User userHolder = userDB.getUser(authUser.getUsername());
		
//	if (userPos.getByUsername(authUser.getUsername()) == null)
//		return false;
//	
//	User userHolder = userPos.getByUsername(authUser.getUsername());
			
			//Check if password check is correct
			if (userHolder.getPassword().equals(authUser.getPassword())) {
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Successful Login");
				return true;
			}
		}
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Invalid Username/Password");
		
		return false;
	}
	

}
