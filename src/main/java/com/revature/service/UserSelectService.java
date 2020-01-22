package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.User;
import com.revature.ui.utility.UtilityPrints;

public class UserSelectService {
	
	private static Scanner scan = new Scanner(System.in);
	
	public User.UserType selectUserType() {
		
		switch (scan.nextLine()) {
		case "1":
			return User.UserType.Customer;
		case "2":
			return User.UserType.Employee;
		case "3":
			return null;
		default:
			UtilityPrints.printInvalidInput();
			return null;
		}
	}
}
