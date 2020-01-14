package com.revature.service;

import com.revature.pojo.User;

public class CustomerLoginService extends UserLoginService {

	public CustomerLoginService () {
		userType = User.UserType.Customer;
	}
	
	@Override
	public void printLoginMessage () {
		System.out.println("Customer Login");
	}
	
	@Override
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Create Account");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	@Override
	public void setUserType (User newUser) {
		newUser.setUserType(User.UserType.Customer);
	}
}
