package com.revature.service;

import java.util.Scanner;

public class CustomerAuctionService extends AuctionService{
	
	private static Scanner scan = new Scanner(System.in);
	
	@Override
	protected void printHeaderMessage () {
		System.out.println("Auction Menu");
	}
	
	@Override
	protected void printOptionMenu () {
		System.out.println("[1] View Cars On Lot");
		System.out.println("[2] Put A Bid On Car");
		System.out.println("[3] Exit Menu");
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
				addBid();
				break;
			case "3":
				return;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
		}
	}
}
