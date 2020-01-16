package com.revature.service;

import java.util.Scanner;

import com.revature.dao.AuctionDatabaseSerialization;
import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.AuctionPosting;
import com.revature.pojo.User;

public class AuctionService {

	private static Scanner scan = new Scanner(System.in);
	protected static AuctionDatabaseSerialization aucDB = new AuctionDatabaseSerialization();
	protected static UserDatabaseSerialization userDB = new UserDatabaseSerialization();
	protected static User currentUser = null;
	
	public void aucServMain (User curUser) {
		aucDB.deserializeDB();
		currentUser = curUser;
		optionChoice();
	}
	
	protected void printHeaderMessage () {
		System.out.println("Auction Menu");
	}
	
	protected void printOptionMenu () {
		System.out.println("Auction Choices");
	}
	
	protected void optionChoice () {
		
	}

	public final AuctionPosting getLotPost () {
		if (aucDB.isEmpty()) {
			return null;
		}
			
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select car's lot number: ");
		Integer postIndex = null;
		
		try {
			postIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid lot number.");
			return null;
		}
		
		if (aucDB.getPost(postIndex - 1) == null) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid lot number.");
			return null;
		}
		
		return aucDB.getPost(postIndex - 1);
	}
	
	public void showAucPosts () {
		if (aucDB.isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("No cars on lot");
			return;
		}
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("||Car Lot");
		for (int i = 0; i < aucDB.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + aucDB.getPost(i).getCar().getCarInfo());
		}
	}

}
