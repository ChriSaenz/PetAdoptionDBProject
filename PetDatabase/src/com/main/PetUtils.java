package com.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.exceptions.LoginFailedException;
import com.objects.User;

public class PetUtils {
	
	static Scanner scan = new Scanner(System.in);

	//	Login system for users.
	//	Returns the type of user that logged in.
	public static Type startUp() {
		System.out.println("Welcome to the Pet Adoption System.");
		
		//	Continue requesting input until valid input is given
		while(true) {
			System.out.println("Choose from the following menu:");
			System.out.println("1. Register account \n2. Login \n0. Quit");
			System.out.print("Selection: ");
			try {
				int choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
					case 0:
						System.out.println("Goodbye");
						System.exit(0);
						return null;
					case 1:
						return registerCustomer();
					case 2:
						return login();
					default:
						System.out.println("Invalid input. Please input a number between 0 and 2.");
						break;
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please input a number between 0 and 2.");
			} catch (Exception e) {
				System.out.println("An error occurred. Please try again.");
			}
		}
	}

	//	Takes login information, finds user in database, and logs user in.
	//	Returns the type of user that logged in.
	private static Type login() {
		System.out.println("Username: ");
		String user = scan.next();
		System.out.println("Password: ");
		String pass = scan.next();
		try {
			return DB.login(user, pass);
		} catch (LoginFailedException e) {
			System.out.println(e.getMessage());
			return startUp();
		}
	}
	
	//	Creates a new user using input information.
	//	Returns customer, since only customers can be registered this way.
	private static Type registerCustomer() {
		System.out.println("Username: ");
		String user = scan.next();
		System.out.println("Password: ");
		String pass = scan.next();
		DB.registerUser(new User(user, pass, Type.CUSTOMER));
		return Type.CUSTOMER;
	}

}
