package com.revature.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.dao.AuctionDatabaseSerialization;
import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class AuctionService {

	private static Scanner scan = new Scanner(System.in);
	private static AuctionDatabaseSerialization aucDB = new AuctionDatabaseSerialization();
	private static UserDatabaseSerialization userSer = new UserDatabaseSerialization();
	private static List<AuctionPosting> aucPosts = null;
	private static User currentUser = null;
	
	public void aucServMain (User curUser) {
		aucPosts = aucDB.deserializeDB();	
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
	
	public void addAuction () {
		aucPosts.add(createAuction());		
		aucDB.serializeDB(aucPosts);
	}
	
	public AuctionPosting createAuction () {		
		AuctionPosting newPost = new AuctionPosting();
		
		newPost.setCar(createCar());
		newPost.setBids(new HashMap<String, Float>());
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Auction Added");
		
		return newPost;
	}
	
	public Car createCar () {
		Car newCar = new Car();
		
		System.out.println("\n||---------------------------------------------||");
		
		System.out.println("Please Enter The Car's Make: ");
		newCar.setMake(scan.nextLine());
		
		System.out.println("Please Enter The Car's Model: ");
		newCar.setModel(scan.nextLine());
		
		System.out.println("Please Enter The Car's Year: ");
		newCar.setYear(scan.nextLine());
		
		newCar.setRemPayments(new ArrayList<Float>());
		
		return newCar;
	}
	
	public boolean removeAuction () {
		int carIndex = getLotIndex();
		
		if (carIndex < 0)
			return false;
		
		aucPosts.remove(carIndex - 1);
		aucDB.serializeDB(aucPosts);
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Removed Car From Lot");
		
		return true;
	}
	
	public void showAucPosts () {
		if (aucPosts.isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("No cars on lot");
			return;
		}
		
		System.out.println("\n||---------------------------------------------||");
		for (int i = 0; i < aucPosts.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + aucPosts.get(i).getCar().getCarInfo());
		}
	}
	
	public void showBidsOnCar () {
		int carIndex = getLotIndex();
		
		if (carIndex < 0)
			return;
		
		if (aucPosts.get(carIndex - 1).getBids().isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("There are no bid currently on this auction");
			return;
		}

		List<String> usernames = new ArrayList<>();
		
		while (true) {
			Iterator bids = aucPosts.get(carIndex - 1).getBids().entrySet().iterator();
			usernames.clear();
			
			while(bids.hasNext()) {
				Map.Entry bid = (Map.Entry) bids.next();
				usernames.add((String) bid.getKey());
				
				System.out.println("[" + usernames.size() + "] " + bid.getValue());
			}
			
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Bids Management");
			System.out.println("[1] Accept a bid");
			System.out.println("[2] Reject a Bid");
			System.out.println("[3] Exit");
			
			switch(scan.nextLine()) {
			case "1":
				acceptBid(carIndex, usernames);
				return;
			case "2":
				removeBid(carIndex, usernames);
				break;
			case "3":
				return;
			default:
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Please select a valid bid.");
				break;
			}
		}
		
	}
	
	public void acceptBid (int postIndex, List<String> usernames) {
		int bidIndex = 0;
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a bid to accept");
		
		try {
			bidIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		try {
			aucPosts.get(bidIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		Map<String, User> userDB = userSer.deserializeDB(User.UserType.Customer.toString());
		Car carToAdd = aucPosts.get(postIndex - 1).getCar();
		String winUsername = usernames.get(bidIndex - 1);
		User winUser = userDB.get(winUsername);
		
		Float bidAmount = aucPosts.get(postIndex - 1).getBids().get(usernames.get(bidIndex - 1));
		
		for (int i = 0; i < 60; i++)
			carToAdd.getRemPayments().add(bidAmount / 60);
		
		winUser.getOwnedCars().add(carToAdd);
		System.out.println(winUser.getOwnedCars().size());
		userSer.updateUser(userDB, winUser);
		
		aucPosts.remove(postIndex - 1);
		aucDB.serializeDB(aucPosts);
		System.out.println("Accepted Bid From Car");
	}
	
	public void removeBid (int carIndex, List<String> usernames) {
		int bidIndex = 0;
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a bid to remove");
		
		try {
			bidIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		try {
			aucPosts.get(bidIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return;
		}
		
		aucPosts.get(carIndex - 1).getBids().remove(usernames.get(bidIndex - 1));
		aucDB.serializeDB(aucPosts);
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Removed Bid From Car\n");
	}
	
	public void addBid() {
		int carIndex = getLotIndex();
		float carBid = 0;
		
		if (carIndex < 0)
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
		
		aucPosts.get(carIndex - 1).getBids().put(currentUser.getUsername(), carBid);
		aucDB.serializeDB(aucPosts);
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Added Bid To Car");
	}
	
	public int getLotIndex () {
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select car's lot number: ");
		Integer carIndex = null;
		
		try {
			carIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid lot number.");
			return -1;
		}
		
		try {
			aucPosts.get(carIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid lot number.");
			return -1;
		}
		
		return carIndex;
	}
	
}
