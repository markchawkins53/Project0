package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.User;

public class EmployeePortalService extends UserPortalService{
	private static EmployeeAuctionService aucServ = new EmployeeAuctionService();
	private static Scanner scan = new Scanner(System.in);

	@Override
	public void userPortServMain (User currentUser) {
		while (true) {
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				aucServ.aucServMain();
				break;
			case "2":
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
		System.out.println("[1] Look At Current Auctions");
		System.out.println("[2] Look At Owned Cars");
		System.out.println("[3] Logout");
	}
}
