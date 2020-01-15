package com.revature.service;

import com.revature.pojo.User;

public class EmployeeLoginService extends UserLoginService {
	
	public EmployeeLoginService () {
		userType = User.UserType.Employee;
	}
	
	@Override
	public void printLoginMessage () {
		System.out.println("Employee Login");
	}
	
	@Override
	public void printOptionMenu () {
		System.out.println("[1]: Login");
		System.out.println("[2]: Regisiter Employee");
		System.out.println("[3]: Exit to Select User Type");
	}
	
	@Override
	public void setUserType () {
		userType = User.UserType.Employee;
	}
}
