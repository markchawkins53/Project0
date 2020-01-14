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

import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class AuctionService {

	private static Scanner scan = new Scanner(System.in);
	private static List<AuctionPosting> aucPosts = null;
	
	public void aucServMain () {
	}
	
	protected void printHeaderMessage () {
		System.out.println("Auction Menu");
	}
	
	protected void printOptionMenu () {
		System.out.println("Auction Choices");
	}
	
	public AuctionPosting createAuction () {
		AuctionPosting newPost = new AuctionPosting();
		
		newPost.setCar(createCar());
		newPost.setBids(new HashMap<String, Float>());
		
		serializeDB();
		
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
	
	public void showAucPosts () {
		for (int i = 0; i < aucPosts.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + aucPosts.get(i).getCar());
		}
	}
	
	private static void serializeDB () {
		String filename = "AuctionDB.dat";
		
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(aucPosts);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void deserializeDB () {
		String filename = "AuctionDB.dat";

		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			aucPosts = (List<AuctionPosting>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("No File");
			aucPosts = new LinkedList<AuctionPosting>();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
