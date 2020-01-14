package com.revature.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.AuctionDatabaseSerialization;
import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class AuctionService {

	private static Scanner scan = new Scanner(System.in);
	private static AuctionDatabaseSerialization aucDB = new AuctionDatabaseSerialization();
	private static List<AuctionPosting> aucPosts = null;
	
	public void aucServMain () {
		aucPosts = aucDB.deserializeDB();		
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
		
		return newPost;
	}
	
	public Car createCar () {
		Car newCar = new Car();
		
		System.out.println("Please Enter The Car's Make: ");
		newCar.setMake(scan.nextLine());
		
		System.out.println("Please Enter The Car's Model: ");
		newCar.setModel(scan.nextLine());
		
		System.out.println("Please Enter The Car's Year: ");
		newCar.setYear(scan.nextLine());
		
		return newCar;
	}
	
	public boolean removeAuction () {
		System.out.println("Please select car's lot number: ");
		Integer carIndex = null;
		
		try {
			carIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please select a valid lot number.");
			return false;
		}
		
		try {
			aucPosts.get(carIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Please select a valid lot number.");
			return false;
		}
		
		aucPosts.remove(carIndex - 1);
		aucDB.serializeDB(aucPosts);
		System.out.println("Remove Car From Lot");
		
		return true;
	}
	
	public void showAucPosts () {		
		System.out.println(aucPosts.size());
		
		for (int i = 0; i < aucPosts.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + aucPosts.get(i).getCar().getCarInfo());
		}
	}
	
}
