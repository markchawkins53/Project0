package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.dao.CarDatabaseSerialization;
import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class EmployeeAuctionService extends AuctionService{

	private static Scanner scan = new Scanner(System.in);
	
	@Override
	protected void printHeaderMessage () {
		System.out.println("Auction Menu");
	}
	
	@Override
	protected void printOptionMenu () {
		System.out.println("[1] View Cars On Lot");
		System.out.println("[2] Manage Bids On Car From Lot");
		System.out.println("[3] Add Car To Lot");
		System.out.println("[4] Remove Car From Lot");
		System.out.println("[5] Exit Menu");
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
				bidManager();
				break;
			case "3":
				addCarLotPost();
				break;
			case "4":
				removeCarLotPost();
				break;
			case "5":
				return;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
		}
	}

//========================================================================
//						Lot Management
//========================================================================
	
	public AuctionPosting addCarLotPost () {		
		Car carToAdd = assignNewCarInfo();
		AuctionPosting aucPost;
		
		aucPost = aucDB.createPost(carToAdd);
		aucDB.addPost(aucPost);
		aucDB.serializeDB();
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Auction Added");
		
		return null;
	}
	
	public Car assignNewCarInfo () {
		CarDatabaseSerialization carDB = new CarDatabaseSerialization();
		String make = "";
		String model = "";
		String year = "";
		
		System.out.println("\n||---------------------------------------------||");
		
		System.out.println("Please Enter The Car's Make: ");
		make = scan.nextLine();
		
		System.out.println("Please Enter The Car's Model: ");
		model = scan.nextLine();
		
		System.out.println("Please Enter The Car's Year: ");
		year = scan.nextLine();		
		
		return carDB.createCar(make, model, year);
	}
	
	public boolean removeCarLotPost () {
		showAucPosts();		
		AuctionPosting aucPost = getLotPost();
		
		if (aucPost == null)
			return false;		

		aucDB.removePost(aucPost);
		aucDB.serializeDB();
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Removed Car From Lot");
		
		return true;
	}
	
	
//========================================================================
//					Bid Management
//========================================================================
	
	//Menu for employees to access functions to manage bids on cars
	public void bidManager () {
		showAucPosts();
		AuctionPosting aucPost = getLotPost();
		printBidsOnCar(aucPost);
		
		System.out.println("\n||=============================================||");
		System.out.println("Bids Management");
		System.out.println("[1] Accept a bid");
		System.out.println("[2] Reject a Bid");
		System.out.println("[3] Exit");
		
		switch(scan.nextLine()) {
		case "1":
			acceptBid(aucPost);
			return;
		case "2":
			removeBid(aucPost);
			break;
		case "3":
			return;
		default:
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid bid.");
			break;
		}
	}
	
	//Gets all users currently on a bid
	public List<String> getUsersOnBid (Map<String, Float> aucBids) {
		List<String> usernames = new ArrayList<>();
		
		Iterator bids = aucBids.entrySet().iterator();
		
		while(bids.hasNext()) {
			Map.Entry bid = (Map.Entry) bids.next();
			usernames.add((String) bid.getKey());
		}
		
		return usernames;
	}
	
	//Prints all the current bids on the car
	public void printBidsOnCar (AuctionPosting aucPost) {
		List<String> usersWithCars;
		
		if (aucPost == null)
			return;
		
		if (aucPost.getBids().isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("There are no bid currently on this auction");
			return;
		}

		usersWithCars = getUsersOnBid(aucPost.getBids());
		
		System.out.println("\n||---------------------------------------------||");
		for (int i = 0; i < usersWithCars.size(); i++) {
			String curUser = usersWithCars.get(i);
			System.out.println("[" + (i + 1) + "] " + curUser + " : " + aucPost.getBids().get(curUser).toString());
		}

	}
	
	public void acceptBid (AuctionPosting aucPost) {
		int bidIndex = 0;
		
		if (aucPost == null || aucPost.getBids().isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("No bids to accept");
			return;
		}

		printBidsOnCar(aucPost);
		List<String> usersOnBid = getUsersOnBid(aucPost.getBids());
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a bid to accept");
		
		try {
			bidIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		if (aucPost.getBids().size() < (bidIndex - 1) && (bidIndex - 1) < 0){
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		userDB.deserializeDB(User.UserType.Customer);
		
		Car carToAdd = aucPost.getCar();
		String winUsername = usersOnBid.get(bidIndex - 1);
		User winUser = userDB.getUser(winUsername);
		
		Float bidAmount = aucPost.getBids().get(winUser.getUsername());
		
		for (int i = 0; i < 60; i++)
			carToAdd.getRemPayments().add(bidAmount / 60);
		
		winUser.getOwnedCars().add(carToAdd);
		
//		UserDatabaseSerialization customerDB = new UserDatabaseSerialization();
//		customerDB.deserializeDB(User.UserType.Customer);
//		customerDB.updateUser(winUser);
//		customerDB.serializeDB();

		userDB.deserializeDB(User.UserType.Customer);
		userDB.updateUser(winUser);
		userDB.serializeDB();
		
		aucDB.removePost(aucPost);
		aucDB.serializeDB();

		System.out.println("\n||---------------------------------------------||");
		System.out.println("Accepted Bid From Car");
	}
	
	public void removeBid (AuctionPosting aucPost) {
		int bidIndex = 0;
		
		if (aucPost == null || aucPost.getBids().isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("No bids to remove");
			return;
		}
		
		printBidsOnCar(aucPost);
		List<String> usersOnBid = getUsersOnBid(aucPost.getBids());
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a bid to remove");
		
		try {
			bidIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		if (aucPost.getBids().size() < (bidIndex - 1) && (bidIndex - 1) < 0){
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		aucPost.getBids().remove(usersOnBid.get(bidIndex - 1));
		aucDB.updatePost(aucPost);
		aucDB.serializeDB();

		System.out.println("\n||---------------------------------------------||");
		System.out.println("Removed Bid From Car\n");
	}
}
