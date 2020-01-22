package com.revature.ui.utility;

public class UtilityPrints {

	public static void printBreak() {
		System.out.println("\n||---------------------------------------------||");
		System.out.println("Please enter a Username to use: ");
	}

	public static void printInvalidInput() {
		System.out.println("Did not understand input. Please select a proper input.");		
	}

	public static void printInvalidInput(String phrase) {
		System.out.println(phrase);		
	}

}
