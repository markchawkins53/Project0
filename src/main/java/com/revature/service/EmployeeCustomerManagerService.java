package com.revature.service;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.dao.UserDatabaseSerialization;
import com.revature.pojo.Car;
import com.revature.pojo.User;

public class EmployeeCustomerManagerService {

	private static Scanner scan = new Scanner (System.in);
	private static UserDatabaseSerialization userDB = new UserDatabaseSerialization();
	private static List<User> users;
	
	public void custManSerMain() {
		users = getUsersFromDB();
		users = getUsersWithCars(users);
		
		while (true) {
			System.out.println("\n||=============================================||");
			printHeaderMessage();
			printOptionMenu();
			
			switch (scan.nextLine()) {
			case "1":
				printUsers(users);
				break;
			case "2":
				getUsersPayments(getUser(selectUser()));
				break;
			case "3":
				return;
			default:
				System.out.println("Did not understand input. Please select a proper input.");
				break;
			}
		}
		
	}
		
	public void printHeaderMessage () {
		System.out.println("User Manager");
	}
	
	public void printOptionMenu () {
		System.out.println("[1] Get Users With Cars");
		System.out.println("[2] View Payments Left On User's Car(s)");
		System.out.println("[3] Exit Menu");
	}
	
	public List<User> getUsersFromDB() {
		userDB.deserializeDB(User.UserType.Customer);
		
		Iterator usersFromDB = userDB.getDbHolder().entrySet().iterator();
		List<User> usersHolder = new LinkedList<>();
		
		while(usersFromDB.hasNext()) {
			Map.Entry DBEntry = (Map.Entry) usersFromDB.next();
			usersHolder.add((User)DBEntry.getValue());
		}
		
		return usersHolder;
	}
	
	public List<User> getUsersWithCars (List<User> usersHolder) {
		if (usersHolder.isEmpty()) {
			return null;
		} 
		
		for (int i = 0; i < usersHolder.size(); i++) {
			if (usersHolder.get(i).getOwnedCars().isEmpty()) {
				usersHolder.remove(i);
				i--;
			}			
		}
		
		return usersHolder;
	}
	
	public User getUser (int userIndex) {
		try {
			users.get(userIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			return null;
		}
		
		return users.get(userIndex - 1);
	}
	
	public int selectUser() {
		int tempIndex = 0;
		
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please select a user with a car");
		
		try {
			tempIndex = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return - 1;
		}
		
		try {
			users.get(tempIndex - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("Please select a valid number");
			return -1;
		}
		
		return tempIndex;
	}
	
	public void getUsersPayments (User userHolder) {
		if (userHolder == null) {
			return;
		}
		
		System.out.println("\n||---------------------------------------------||");
		for (Car c: userHolder.getOwnedCars()) {
			int paymentsLeft = c.getRemPayments().size();
			Float amountPerPayment = c.getRemPayments().get(0);
			DecimalFormat decialHund = new DecimalFormat("#.##");
			System.out.println(c.getCarInfo() + " : " + paymentsLeft + " months left @ " + decialHund.format( (double)amountPerPayment) + " each.");
		}
	}
	
	public void printUsers (List<User> userHolder) {
		if (userHolder.isEmpty()) {
			System.out.println("\n||---------------------------------------------||");
			System.out.println("No customers with cars");
			return;
		}
		
		System.out.println("\n||---------------------------------------------||");
		for(int i = 0; i < userHolder.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + userHolder.get(i).getUsername());
		}
	}
	
}
