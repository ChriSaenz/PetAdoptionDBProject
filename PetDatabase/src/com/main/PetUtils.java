package com.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.InvalidSearchException;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Request;

public class PetUtils {

	static Scanner scan = new Scanner(System.in);

	//	Basic menu, before employee/admin login. Accessible to all customers by default.
	public static void menu() {
		System.out.println("Welcome to the pet adoption system");
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("1. Employee login");
			System.out.println("2. View all pets");
			System.out.println("3. Filter pets by species");
			System.out.println("4. Filter pets by age");
			System.out.println("5. View pet by name");
			System.out.println("6. Request adoption"); // prompts to either use pre-existing profile or create new

			System.out.print("0. Quit");
			System.out.print("Selection: ");
			try {
				int choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				//	0. Quit
				case 0:
					System.out.println("Goodbye");
					System.exit(0);
					break;
				//	1. Employee login
				case 1:
					System.out.print("Enter username: ");
					String username = scan.next();
					System.out.print("Enter password: ");
					String password = scan.next();
					try {
						Employee e = DB.findEmployee(username, password);
						System.out.println("Success! Hello " + e.getName());
					} catch (InvalidSearchException i) {
						System.out.println(i.getMessage());
					}
					employeeMenu();
					break;
				//	TODO: Write 2. View all pets
				case 2:
					break;
				//	5. View pet by name
				case 5: {
					System.out.println("Enter the name of the pet.");
					String petName = scan.nextLine();
					System.out.println("Fetching results...");
					try {
						List<Pet> results = DB.fetchPets("name", petName);
						if (results.size() == 0) {
							System.out.println("Sorry, no pets were found with the name " + petName + ".");
						}
						else for (Pet p : results) {
							System.out.println(p.toString());
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
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
		System.out.println("Welcome employee");
		while (true) {
			System.out.println("Choose from the following menu:");
			System.out.println("0. Logout (return to previous menu)");
			System.out.println("1. View pending adoption requests");
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
				//	0. Logout (return to previous menu)
				case 0:
					return;
				//	1. View open adoption requests
				case 1: {
					try {
						List<Request> requests = DB.fetchRequests("status", "Pending");
						if (requests.size() == 0) {
							System.out.println("No open adoption requests");
						}
						else for (Request r : requests) {
							System.out.println(r.toString());
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				//	2. View all adoption requests
				case 2: {
					try {
						List<Request> requests = DB.fetchRequests();
						if (requests.size() == 0) {
							System.out.println("No adoption requests found");
						}
						else for (Request r : requests) {
							System.out.println(r.toString());
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				//	TODO: 3. Approve/reject adoption request
				//	TODO: 4. View all customers
				//	TODO: 5. View specific customer
				//	TODO: 6. View adoption logs
				//	TODO: 7. Add new pet
				case 8:
					System.out.print("Enter username: ");
					String username = scan.next();
					System.out.print("Enter password: ");
					String password = scan.next();
					try {
						Employee e = DB.findEmployee(username, password);
						if (e.isAdmin()) {
							System.out.println("Success! Hello " + e.getName());
							adminMenu();
						} else {
							System.out.println("Employee " + e.getName() + " is not an admin");
						}
					} catch (InvalidSearchException i) {
						System.out.println(i.getMessage());
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
				//	TODO: 1. Create employee
				case 1:
					break;
				//	2. Remove employee
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
				//	3. View employees
				case 3: {
					try {
						List<Employee> employees = DB.fetchUsers();
						if (employees.size() == 0) {
							System.out.println("No employees found");
						}
						else for (Employee emp : employees) {
							System.out.println(emp.toString());
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				default: break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Try again.");
			}
		}
	}

}