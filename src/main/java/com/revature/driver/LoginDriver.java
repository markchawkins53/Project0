package com.revature.driver;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.pojo.User;
import com.revature.service.UserLoginService;

public class LoginDriver {
	
	private static UserLoginService uls = new UserLoginService();
	private static Scanner scan = new Scanner(System.in);
	
	public void logDriverMain () {
		User userInfo;
		
		while (true) {
			printloginMessage();
			printOptionMenu();
			
			switch (getMenuChoice()) {
			case "1":
				userInfo = getUserInfoInput();
				checkLoginInfo(userInfo);
				break;
			case "2": 
				createUser();
				break;
			case "3":
				scan.close();
				return;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
			
		}
	}
	
	public String getMenuChoice () {
		String choice = "";
		
		try {
			choice = scan.nextLine();
		} catch (NoSuchElementException e) {
			System.out.println("Did not understand input. Please select a proper input");
			return null;
		}
		
		return choice;
	}
	
	public void printloginMessage () {
		System.out.println("User Login");
	}
	
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Register");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	public void createUser () {
		User newUser = new User();
		
		while (true) {
			System.out.println("Please enter a Username to use: ");
			
			newUser.setUsername(scan.nextLine());
			if (uls.checkUserExists(newUser.getUsername())) {
				System.out.println("Username currently in use.");
				continue;
			}
			
			System.out.println("Please enter a Password to use: ");
			newUser.setPassword(scan.nextLine());
			//uls.registerUser(newUser.getUsername(), newUser.getPassword());			
			break;
		}
			
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
		if (uls.authenticateUser(loginInfo)) {
			System.out.println("Successful Login");
			return true;
		}
				
		System.out.println("Invalid Username/Password");
		
		return false;
	}
	
	
}
