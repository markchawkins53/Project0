package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.AuctionPosting;

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
			System.out.println("\n||=============================================||");
			printHeaderMessage();
			printOptionMenu();
			
			switch(scan.nextLine()) {
			case "1":
				showAucPosts();
				break;
			case "2":
				showAucPosts();
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
	
	public void addBid() {		
		AuctionPosting aucPost = getLotPost();
		float carBid = 0;
		
		if (aucPost == null)
			return;
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("How much would like to bid for the car?");
		
		try {
			carBid = Float.parseFloat(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		aucPost.getBids().put(currentUser.getUsername(), carBid);
		aucDB.updatePost(aucPost);
		aucDB.serializeDB();
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Added Bid To Car");
	}
	
}
