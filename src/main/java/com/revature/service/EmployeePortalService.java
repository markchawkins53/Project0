package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.User;

public class EmployeePortalService extends UserPortalService{
	private static EmployeeAuctionService aucServ = new EmployeeAuctionService();
	private static EmployeeCustomerManagerService custManSer = new EmployeeCustomerManagerService();
	private static Scanner scan = new Scanner(System.in);

	@Override
	public void userPortServMain (User curUser) {
		while (true) {
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				aucServ.aucServMain(curUser);
				break;
			case "2":
				custManSer.custManSerMain();
				break;
			case "3":
				return;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
			
		}
	}
	
	@Override
	public void printHeaderMessage () {
		System.out.println("Employee Portal");
	}
	
	@Override
	public void printOptionMenu () {
		System.out.println("[1] Auctions Manager");
		System.out.println("[2] Customer Manager");
		System.out.println("[3] Logout");
	}
}
