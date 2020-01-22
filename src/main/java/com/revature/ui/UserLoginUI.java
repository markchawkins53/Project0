package com.revature.ui;

import java.util.Scanner;

import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.User;
import com.revature.service.UserLoginService;

public class UserLoginUI {
	
	private static UserLoginService uls = new UserLoginService();
	
	//Message displayed before Options Menu
	public void printHeaderMessage () {
		System.out.println("\n||=============================================||");
		System.out.println("User Login");
	}
	
	//Message displayed with all the choices the user will be able to do
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Register");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	public User userLoginMenu () {
		printHeaderMessage();
		printOptionMenu();
		
		
		return null;
	}
	
	
}
