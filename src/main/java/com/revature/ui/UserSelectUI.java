package com.revature.ui;

import com.revature.pojo.User;
import com.revature.service.UserSelectService;

public class UserSelectUI {

	private static UserSelectService uss = new UserSelectService();
	
	public void printHeaderMessage() {
		System.out.println("||=============================================||");	
	}

	public void printOptionChoices() {
		System.out.println("[1]: Login as Customer");
		System.out.println("[2]: Login as Employee");
		System.out.println("[3]: Exit Program");		
	}
	
	public User.UserType userSelectMenu() {
			printHeaderMessage();
			printOptionChoices();
			
			return uss.selectUserType();
	}

}
