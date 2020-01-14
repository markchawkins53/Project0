package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.User;

public class CustomerPortalService extends UserPortalService {
	
	private static CustomerAuctionService aucServ = new CustomerAuctionService();
	private static CustomerCarManagerService carManSer = new CustomerCarManagerService();
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
				carManSer.carManServMain(curUser);
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
		System.out.println("Customer Portal");
	}
	
	@Override
	public void printOptionMenu () {
		System.out.println("[1] Auction Manager");
		System.out.println("[2] Owned Car Manager");
		System.out.println("[3] Logout");
	}
}
