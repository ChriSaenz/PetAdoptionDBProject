package com.main;

import java.util.Calendar;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.InvalidSearchException;
import com.objects.Customer;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Receipt;
import com.objects.Request;

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
			// System.out.println("6. Request adoption"); // prompts to either use
			// pre-existing profile or create new
			System.out.println("0. Quit");
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
					try {
						Employee e = DB.findEmployee(username, password);
						System.out.println("Success! Hello " + e.getName());
						employeeMenu(e);
					} catch (InvalidSearchException i) {
						System.out.println(i.getMessage());
					}
					break;
				case 2:
					break;
				case 5:
					System.out.println("Enter the name of the pet.");
					scan.nextLine();
					String petName = scan.nextLine();
					List<Pet> results = DB.fetchPetsByColumnValue("name", petName);
					if (results.size() == 0) {
						System.out.println("Sorry, no pets were found with the name " + petName);
					} else
						for (Pet p : results) {
							System.out.println(p.toString());
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

	private static Customer promptNewCustomer() {
		System.out.print("Input customer name: ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.print("Input customer phone number: ");
		String phone = scan.nextLine();
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		System.out.print("Input customer birthdate (YYYY-MM-DD): ");
		try {
			String bday = scan.nextLine();
			Date date1 = Date.valueOf(bday);
			Customer c = new Customer(name, phone, date, date1);
			DB.insertCustomer(c);
			return c;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid birthdate");
			return null;
		}
	}

	private static Customer promptOldCustomer() {
		Customer c;
		while (true) {
			System.out.print("Customer ID: ");
			try {
				int id = scan.nextInt();
				try {
					c = DB.findCustomer(id);
					System.out.println("Found profile: " + c.getName());
					return c;
				} catch (InvalidSearchException e) {
					System.out.println(e.getMessage());
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input an integer");
			}

		}
		return null;
	}

	private static void employeeMenu(Employee employee) {
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
			System.out.println("9. Enter adoption request");

			System.out.print("Selection: ");
			try {
				int choice = scan.nextInt();
				switch (choice) {
				case 0:
					return;
				case 1:
					// SELECT * FROM <adoptionRequests> WHERE <isOpen>
					break;
				case 6:
					List<Receipt> list = DB.getReceipts();
					System.out.println("Adoption Logs");
					for (Receipt r : list) {
						System.out.println(r);
					}
					break;
				case 8:
					try {
						DB.findEmployee(employee.getUsername(), employee.getPassword());
						if (employee.isAdmin()) {
							System.out.println("Success! Hello " + employee.getName());
							adminMenu();
						} else {
							System.out.println("Employee " + employee.getName() + " is not an admin");
						}
					} catch (InvalidSearchException i) {
						System.out.println(i.getMessage());
					}
					break;
				case 9:
					while (true) {
						System.out.print("Is this a returning customer? Type y or n: ");
						String response = scan.next();
						Customer c = null;
						switch (response.toLowerCase()) {
						case "y":
							c = promptOldCustomer();
							break;
						case "n":
							c = promptNewCustomer();
							break;
						default:
							System.out.println("Try again");
							break;

						}
						Pet pet = promptPet();
						if (c != null && pet != null) {
							System.out.println("Found pet: " + pet.getName());
							Request r = new Request(c.getId(), pet.getId(), employee.getId());
							DB.insertRequest(r);
						} else {
							System.out.println("Encountered error");
						}
						break;
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

	private static Pet promptPet() {
		System.out.print("Enter pet ID: ");
		try {
			return DB.findPet(scan.nextInt());
		} catch (InputMismatchException e) {
			System.out.println("Input an integer");
		} catch (InvalidSearchException i){
			System.out.println(i.getMessage());
		}
		return null;
	}

	private static void adminMenu() {
		System.out.println("Welcome admin");
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("0. Logout (return to previous menu)");
			System.out.println("1. Create employee");
			System.out.println("2. Remove employee");
			System.out.println("3. View employees");
			System.out.print("Selection: ");
			try {
				int choice = scan.nextInt();
				switch (choice) {
				case 0:
					return;
				case 1:
					break;
				case 2:
					System.out.print("Employee ID: ");
					int id = scan.nextInt();
					try {
						Employee e = DB.findEmployee(id);
						DB.removeEmployee(id);
						System.out.println("Employee " + e.getName() + " deleted successfully.");
					} catch (InvalidSearchException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Try again.");
			}
		}
	}

}