package com.revature.ui;

public class MenuHeader implements MenuHeaderUI {

	@Override
	public void printHeaderMessage() {
		System.out.println("||=============================================||");
	}

	@Override
	public void printOptionChoices() {
		System.out.println("[1]: Login as Customer");
		System.out.println("[2]: Login as Employee");
		System.out.println("[3]: Exit Program");		
	}
}
