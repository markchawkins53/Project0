package com.revature.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class CustomerCarManagerService {
	UserDatabaseSerialization userDB = new UserDatabaseSerialization();

	private Scanner scan = new Scanner(System.in);
	private User currentUser = null;
	
	public void carManServMain (User curUser) {
		userDB.deserializeDB(User.UserType.Customer);
		currentUser = curUser;
		
		while (true) {
			System.out.println("\n||=============================================||");
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				printUserCars();
				break;
			case "2":
				printPaymentsLeftOnCar(getCar());
				break;
			case "3":
				makePayment();
				break;
			case "4":
				return;
			default:
				System.out.println("\n||---------------------------------------------||");
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
		}
	}
	
	public void printHeaderMessage () {
		System.out.println("Owned Car Manager");
	}
	
	public void printOptionMenu () {
		System.out.println("[1] Look At Owned Cars");
		System.out.println("[2] View Payments Left On Owned Car");
		System.out.println("[3] Make Payment On Owned Car");
		System.out.println("[4] Exit");
	}
	
	public void printUserCars () {
		List<Car> ownedCars = currentUser.getOwnedCars();
		
		if (ownedCars.isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("You currently have no owned cars");
			return;
		}
		
		System.out.println("\n||---------------------------------------------||");
		for(int i = 0; i < ownedCars.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + ownedCars.get(i).getCarInfo());
		}
			
	}
	
	public Car getCar() {
		int ownedCarIndex = 0;
		
		printUserCars();
		
		if (currentUser.getOwnedCars().isEmpty())
			return null;
			
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a car you own");
		
		try {
			ownedCarIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return null;
		}
		
		try {
			currentUser.getOwnedCars().get(ownedCarIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return null;
		}
		
		return currentUser.getOwnedCars().get(ownedCarIndex - 1);
	}
	
	public void printPaymentsLeftOnCar(Car ownedCar) {
		if (ownedCar == null)
			return;
		
		int paymentsLeft = ownedCar.getRemPayments().size();
		Float amountPerPayment = ownedCar.getRemPayments().get(0);
		DecimalFormat decialHund = new DecimalFormat("#.##");
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("You have " + paymentsLeft + " months left @ " + decialHund.format( (double)amountPerPayment) + " each.");
	}
	
	public boolean makePayment () {
		Car chosenCar = getCar();
		
		printPaymentsLeftOnCar(chosenCar);
		
		if (chosenCar == null)
			return false;
		
		if (chosenCar.getRemPayments().isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("There are no payments to be made");
			return false;
		}
		
		chosenCar.getRemPayments().remove(chosenCar.getRemPayments().size() - 1);
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Payment Successful");
		
		userDB.updateUser(currentUser);
		userDB.serializeDB();
		
		return true;
	}
}
