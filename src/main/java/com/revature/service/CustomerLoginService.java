package com.revature.service;

import com.revature.pojo.User;

public class CustomerLoginService extends UserLoginService {

//========================================================================
//			Methods Overridden from parent
//========================================================================	
	@Override
	public void printHeaderMessage () {
		System.out.println("Customer Login");
	}
	
	@Override
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Create Account");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	@Override
	public void setUserType () {
		userType = User.UserType.Customer;
	}
}
