package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.pojo.Car;
import com.revature.pojo.User;

public class CustomerCarManagerService {

	private Scanner scan = new Scanner(System.in);
	private User currentUser = null;
	
	public void carManServMain (User curUser) {
		currentUser = curUser;
		
		while (true) {
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				viewUserCars();
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
	
	public void viewUserCars () {
		List<Car> ownedCars = currentUser.getOwnedCars();
		
		if (ownedCars.isEmpty()) {
			System.out.println("You currently have no owned cars");
			return;
		}
		
		for(int i = 0; i < ownedCars.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + ownedCars.get(i).getCarInfo());
		}
			
	}
	
	public void printHeaderMessage () {
		System.out.println("Owned Car Manager");
	}
	
	public void printOptionMenu () {
		System.out.println("[1] Look At Owned Cars");
		System.out.println("[2] faojan");
		System.out.println("[3] Logout");
	}
}
