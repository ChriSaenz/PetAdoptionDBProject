package src.com.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.com.exceptions.InvalidSearchException;
import src.com.objects.Employee;
import src.com.objects.Pet;
import src.com.objects.Request;

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
				int choice = scan.nextInt();
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
				//	TODO: Write 2. View all pets. Might want to make it pretty.
				case 2:
					System.out.print("Printing all Pets:\n\t");
					List<String> petNames = DB.getAllPetsName();
					
					for(String n : petNames)
						System.out.print(n + "\n\t");
					break;
				
				//TODO:
				// 3. Filter pets by species
				case 3:
					// First option among all species
					System.out.println("Select the species:");
					int speciesOption = 0;
					List<String> allSpecies = DB.getAllPetSpecies();
					
					
					for(String s : allSpecies)
						System.out.print("\t" + ++speciesOption + ". " + s + "\n");
					
					
					System.out.print("Enter Species Number: ");
					choice = scan.nextInt();
					System.out.println("Displaying " + allSpecies.get(choice - 1) + "s pet names");
					
					List<String> petNamesBySpecies = DB.getAllPetsNameBySpecies(allSpecies.get(choice - 1));
					
					for(String n : petNamesBySpecies)
						System.out.print("\t" + n + "\n");
					
					break;
					
				//	5. View pet by name
				case 5: {
					System.out.println("Enter the name of the pet.");
					String petName = scan.nextLine();
					try {
						List<Pet> results = DB.fetchPets("name", petName);
						if (results.size() == 0) {
							System.out.println("Sorry, no pets were found with the name " + petName);
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
				//	0. Logout (return to previous menu)
				case 0:
					return;
				//	1. View open adoption requests
				case 1: {
					try {
						//	TODO: Is "Open" or "Pending" what we're looking for?
						List<Request> requests = DB.fetchRequests("status", "Open");
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
				// 3. Approve/reject adoption request
				case 3:
					//Enter: Look for specific Request
					//Enter: Get valid Employee ID to aprove it
					System.out.println("Enter Request Number");
					int requestNum = scan.nextInt();
					System.out.println("Enter Employee ID");
					int empID = scan.nextInt();
					
					//Validate Employee ID
					
					System.out.println("Enter Status Decisioooon\n\t0. Rejected\n\t1. Approved");
					System.out.print("Your choice: ");
					int isApproved = scan.nextInt();
					DB.changeRequestStatus(requestNum, empID, isApproved);
					
					System.out.println("Request has been " + ((isApproved == 0) ? "Rejected" : "Approved"));
					
					//Update: Change its String to Approved.
					
					break;
				
				
				//	TODO: 4. View all customers
					
					
				//	TODO: 5. View specific customer (Doesn't check for invalid ID yet
				case 5:
					System.out.print("Enter Customer ID: ");
					int customerID = scan.nextInt();
					
					System.out.println("Displaying info...\n" + DB.findCustomer(customerID));
					
					break;
					
					
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
					
					
				// 1. Create employee
				case 1:
					Employee employee = new Employee();
					
					System.out.println("Enter New Employee Username");
					String username = scan.next();
					System.out.println("Enter New Employee Password");
					String password = scan.next();
					System.out.println("Enter New Employee Name");
					scan.nextLine();
					String name = scan.nextLine();
					System.out.println("Enter New Employee Phone Number (Single String)");
//					scan.nextLine();
					String phone = scan.nextLine();
					System.out.println("Enter New Employee Salary");
					double salary = scan.nextDouble();
					System.out.println("Enter New Employee Title");
					String title = scan.next();
					System.out.println("Enter Employee Admin Status (true or false)");
					boolean admin = scan.nextBoolean();
					
					employee.setUsername(username);
					employee.setPassword(password);
					employee.setName(name);
					employee.setPhone(phone);
					employee.setSalary(salary);
					employee.setTitle(title);
					employee.setAdmin(admin);
					
					DB.insertEmployee(employee);
					System.out.println("Employee added to DB");
					return;
					
					
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