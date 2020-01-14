package com.revature.service;

import java.util.HashMap;
import java.util.Scanner;

import com.revature.pojo.AuctionPosting;

public class EmployeeAuctionService extends AuctionService{

	private static Scanner scan = new Scanner(System.in);
	
	@Override
	protected void printHeaderMessage () {
		System.out.println("Auction Menu");
	}
	
	@Override
	protected void printOptionMenu () {
		System.out.println("[1] View Cars On Lot");
		System.out.println("[2] Look At Bids On Car From Lot");
		System.out.println("[3] Add Car To Lot");
		System.out.println("[4] Remove Car From Lot");
		System.out.println("[5] Exit Menu");
	}
	
	@Override
	protected void optionChoice () {
		while (true) {
			printHeaderMessage();
			printOptionMenu();
			
			switch(scan.nextLine()) {
			case "1":
				showAucPosts();
				break;
			case "2":
				break;
			case "3":
				addAuction();
				break;
			case "4":
				removeAuction();
				break;
			case "5":
				return;
			default:
				break;
			}
		}
	}
}
