package com.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetUtils {

	static Scanner scan = new Scanner(System.in);

	public static void menu() {
		System.out.println("Welcome to the pet adoption system");
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("1. Employee login");
			System.out.println("2. View all pets");
			System.out.println("3. Filter pets by species");
			System.out.println("4. Filter pets by age");
			System.out.println("5. View pet by name");
			System.out.println("6. Request adoption"); //prompts to either use pre-existing profile or create new
			

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
					System.out.print("Enter username: ");
					String username = scan.next();
					System.out.print("Enter password: ");
					String password = scan.next();
					if (DB.login(username, password)) {
						employeeMenu();
					} else {
						System.out.println("Unable to verify employee");
					}
					break;
				case 2:
					break;
				default:
					System.out.println("Try again");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Input an integer");
			}
		}
	}

	private static void employeeMenu() {
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("0. Logout (return to previous menu)");
			System.out.println("1. View open adoption requests");
			System.out.println("2. View all adoption requests");
			System.out.println("3. Approve/reject adoption request");
			System.out.println("4. View all customers");
			System.out.println("5. View specific customer");
			System.out.println("6. View adoption logs");
			System.out.println("7. Add new pet");
			System.out.println("8. Admin login");

			System.out.print("Selection: ");
			try {
			int choice = scan.nextInt();
			switch (choice) {
			case 0:
				return;
			case 8:
				System.out.print("Enter username: ");
				String username = scan.next();
				System.out.print("Enter password: ");
				String password = scan.next();
				if (DB.adminLogin(username, password)) {
					adminMenu();
				} else {
					System.out.println("Unable to verify admin");
				}
				break;
			default:
				System.out.println("Try again");
				break;
			}
			} catch (InputMismatchException e) {
				System.out.println("Input an integer");
			}
		}
		
	}

	private static void adminMenu() {
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("0. Logout (return to previous menu)");
			System.out.println("1. Create employee");
			System.out.println("2. Remove employee");
			System.out.println("3. View employees");


		}	
	}

}
