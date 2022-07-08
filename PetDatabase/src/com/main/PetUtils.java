package com.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.exceptions.LoginFailedException;
import com.objects.User;

public class PetUtils {
	
	static Scanner scan = new Scanner(System.in);

	public static Type startUp() {
		System.out.println("Welcome to the pet adoption system");
		System.out.println("Choose from the following menu:");
		System.out.print("1. Register account");
		System.out.print("2. Login");
		System.out.print("0. Quit");
		System.out.print("Selection: ");
		try {
			int choice = scan.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Goodbye");
				System.exit(0);
				break;
			case 1:
				return registerCustomer();
			case 2:
				return login();
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Input an integer 0-2");
			return startUp();
		}
		return null;
	}

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
	


	private static Type registerCustomer() {
		System.out.println("Username: ");
		String user = scan.next();
		System.out.println("Password: ");
		String pass = scan.next();
		DB.registerUser(new User(user, pass, Type.CUSTOMER));
		
		return Type.CUSTOMER;
	}

}
