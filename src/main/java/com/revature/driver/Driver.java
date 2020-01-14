package com.revature.driver;

import java.util.Scanner;

import com.revature.pojo.User;
import com.revature.service.*;

public class Driver {
	
	private static UserLoginService logDriver;
	private static UserPortalService portServ;
	private static User currentUser;
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main (String[] args) {
		while (true) {
			if (!userLogin())
				return;
			
			if (currentUser.getUserType().equals(User.UserType.Customer)) {
				portServ = new CustomerPortalService();
				portServ.userPortServMain(currentUser);
			}
			else if (currentUser.getUserType().equals(User.UserType.Employee)) {
				portServ = new EmployeePortalService();
				portServ.userPortServMain(currentUser);
			}
		}
	}
	
	private static boolean userLogin() {
		while (true) {
			System.out.println("[1]: Login as Customer");
			System.out.println("[2]: Login as Employee");
			System.out.println("[3]: Exit Program");
			
			switch (scan.nextLine()) {
			case "1":
				logDriver = new CustomerLoginService();
				currentUser = logDriver.logDriverMain();
				
				if (currentUser != null)
					return true;
				
				break;
			case "2":
				logDriver = new EmployeeLoginService();
				currentUser = logDriver.logDriverMain();
				
				if (currentUser != null)
					return true;
				
				break;
			case "3":
				return false;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
			
		}
	}
}
