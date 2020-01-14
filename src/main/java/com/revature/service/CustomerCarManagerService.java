package com.revature.service;

import java.text.DecimalFormat;
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
				viewPaymentsLeftOnCar();
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
	
	public void viewPaymentsLeftOnCar() {
		int ownedCarIndex = 0;
		System.out.println("Please select a car you own");
		
		try {
			ownedCarIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please select a valid number");
			return;
		}
		
		try {
			currentUser.getOwnedCars().get(ownedCarIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Please select a valid number");
			return;
		}
		
		int paymentsLeft = currentUser.getOwnedCars().get(ownedCarIndex - 1).getRemPayments().size();
		Float amountPerPayment = currentUser.getOwnedCars().get(ownedCarIndex - 1).getRemPayments().get(0);
		DecimalFormat decialHund = new DecimalFormat("#.##");
		
		System.out.println("You have " + paymentsLeft + " months left @ " + decialHund.format( (double)amountPerPayment) + " each.");
	}
	
	public void printHeaderMessage () {
		System.out.println("Owned Car Manager");
	}
	
	public void printOptionMenu () {
		System.out.println("[1] Look At Owned Cars");
		System.out.println("[2] View Payments Left On Owned Car");
		System.out.println("[3] Logout");
	}
}
