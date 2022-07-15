package com.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exceptions.InvalidSearchException;
import com.objects.Customer;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Receipt;
import com.objects.Request;

public class DB {

	static Connection con;

	private static void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.print("Driver loaded ||| ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adoption_system", "root", "Password123");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Creates and returns a list of all employees.
	public static List<Employee> fetchUsers() {
		dbConnect();
		String sql = "select * from employee";
		List<Employee> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next()) {
				String name = r.getString("name");
				double salary = r.getDouble("salary");
				String phone = r.getString("phone");
				String title = r.getString("title");
				boolean admin = r.getBoolean("admin");
				String username = r.getString("username");
				String password = r.getString("password");
				Employee e = new Employee(name, phone, salary, username, password, admin, title);
				e.setId(r.getInt("id"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	public static List<Pet> fetchPets() {
		dbConnect();

		String sql = "SELECT * FROM Pet";
		List<Pet> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Pet p = new Pet(r.getInt("request_id"), r.getString("name"), r.getString("species"), r.getInt("age"),
						r.getString("date_acquired"), r.getString("sex"), r.getString("color"), r.getString("breed"),
						r.getBoolean("vaccinated"), r.getBoolean("neutered"), r.getDouble("cost"));
				p.setId(r.getInt("id"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	public static List<Pet> fetchPets(String column, String value) {
		dbConnect();

		String sql = "SELECT * FROM Pet WHERE " + column + " = ?";
		List<Pet> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Pet p = new Pet(r.getInt("request_id"), r.getString("name"), r.getString("species"), r.getInt("age"),
						r.getString("date_acquired"), r.getString("sex"), r.getString("color"), r.getString("breed"),
						r.getBoolean("vaccinated"), r.getBoolean("neutered"), r.getDouble("cost"));
				p.setId(r.getInt("id"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	
	// Diego: Amazing method idea!
	// TODO: Test these methods for correctness
	public static List<Request> fetchRequests() {
		return fetchRequests(null, null);
	}

	public static List<Request> fetchRequests(String column, String value) {
		dbConnect();

		String sql = "SELECT * FROM request";
		List<Request> list = new ArrayList<>();
		if (column != null && value != null)
			sql = sql + " WHERE " + column + " = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if (column != null && value != null) {
				ps.setString(1, value);
			}
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Request req = new Request(r.getInt("id"), r.getInt("customer_id"), r.getInt("pet_id"), r.getDate("date"),
						r.getString("status"), r.getInt("employee_id"));
				list.add(req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	public static Employee findEmployee(String username, String password) throws InvalidSearchException {
		
		// Diego: Might be wasteful if database is large to get a copy of all users everytime
		// ask professor if its more efficient to get employee with sql query
		// select name from employee where username = ? AND password = ?
		// Name would only be used to check if rst is null (No results) and so on
		
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		throw new InvalidSearchException("Employee " + username + " does not exist");
	}

	public static Employee findEmployee(int id) throws InvalidSearchException {
		// Diego: Same as above
		
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		throw new InvalidSearchException("Employee " + id + " does not exist");
	}

	public static void removeEmployee(int id) {
		dbConnect();
		String sql = "delete from employee where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();

	}
	
	//	I couldn't find these two methods anymore so I wrote new ones - Chris
	public static List<Receipt> getReceipts() {
		dbConnect();

		String sql = "SELECT * FROM receipt";
		List<Receipt> list = new ArrayList<>();
//		if (column != null && value != null)
//			sql = sql + " WHERE " + column + " = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
//			if (column != null && value != null) {
//				ps.setString(1, value);
//			}
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Receipt rec = new Receipt(r.getInt("employee_id"),
										r.getInt("customer_id"),
										r.getInt("request_id"),
										r.getString("date"),
										r.getDouble("cost"));
				list.add(rec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
	public static void insertCustomer(Customer customer) {
		dbConnect();
		String sql = "insert into customer(name, phone_number, date_joined, birthday) " + "values (?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhone());
			pstmt.setDate(3, customer.getDate_joined());
			pstmt.setDate(4, customer.getBirthdate());

			pstmt.executeUpdate();
			
			System.out.println("Customer added to DB");

			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

		dbClose();
	}

	// Diego's Additions
	// field to print debug Text
	static boolean debugPrints = false;

	// TODO: Maybe add this as an option
	public static boolean switchDebugPrints() {
		System.out.println("DebugPrints " + !debugPrints);
		return debugPrints;
	}

	// Diego's note: Returns a list of all Pets name
	public static List<String> getPetsName() {
		if (debugPrints)
			System.out.println("[GetPetsName]");
		dbConnect();

		String sql = "select name from Pet";
		List<String> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while (rst.next())
				list.add(rst.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	// Diego's note: Returns a list of all Species
	public static List<String> getPetSpecies() {
		if (debugPrints)
			System.out.println("[GetPetsSpecies]");
		dbConnect();

		String sql = "select distinct species from Pet";
		// select name, id from Employee
		// return Map<String, String>
		// Key id and value is name
		List<String> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while (rst.next())
				list.add(rst.getString("species"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	// Diego's note: Returns a list of all Pets name by Species
	// TODO: (Does not check for Unlisted Species)
	public static List<String> getAllPetsNameBySpecies(String species) {
		if (debugPrints)
			System.out.println("[GetAllPetsNameBySpecies]");
		dbConnect();

		String sql = "select name from Pet where species = ?";
		List<String> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, species);
			ResultSet rst = pstmt.executeQuery();

			while (rst.next())
				list.add(rst.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	// Diego's Note: Returns a copy of a customer with certain ID
	public static Customer findCustomer(int customerID) {
		if (debugPrints)
			System.out.println("[findCustomer]");
		dbConnect();

		Customer cus = new Customer();
		String sql = "select * from Customer where id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerID);
			ResultSet rst = pstmt.executeQuery();
			rst.next();

			int nid = rst.getInt("id");
			String nname = rst.getString("name"), nphone = rst.getString("phone_number");
			Date ndate = rst.getDate("date_joined"), nbirthday = rst.getDate("birthday");

			cus = new Customer(nid, nname, nphone, ndate, nbirthday);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return cus;
	}

	// Diego's Note: Inserts a new employee into the Employee Table
	public static void insertEmployee(Employee emp) {
		if (debugPrints)
			System.out.println("[insertEmployee]");
		dbConnect();

		String sql = "insert into Employee (username, password, name, phone, salary, title, admin) values"
				+ "(?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// Values start at 1 index
			pstmt.setString(1, emp.getUsername());
			pstmt.setString(2, emp.getPassword());
			pstmt.setString(3, emp.getName());
			pstmt.setString(4, emp.getPhone());
			pstmt.setDouble(5, emp.getSalary());
			pstmt.setString(6, emp.getTitle());
			pstmt.setBoolean(7, emp.isAdmin());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	// Diego's Note: Takes a RequestID and EmployeeID to change
	// Request Status to Approved or Rejected.
	public static void changeRequestStatus(int requestNum, int empID, int status) {
		System.out.println("[changeRequestStatus]");
		dbConnect();
		String sql = "update Request SET status = ? where id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (status == 1) ? "Approved" : "Rejected");
			pstmt.setInt(2, requestNum);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	/* Diego's Note: Returns a "Tutple" of 2 colums
	 * <Key: id, Value: column>
	 * 
	 * @param table: Table name to fetch from
	 * @param field: field you want along id
	 */
	public static Map<Integer, String> getXTuple(String table, String field) {
		if (debugPrints)
			System.out.println("[getXTuple]");
		dbConnect();

		String sql = "select id, " + field + " from " + table;
		Map<Integer, String> tuples = new HashMap<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while (rst.next())
				tuples.put(rst.getInt("id"), rst.getString(field));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return tuples;
	}
	
	/* Diego's Note: Returns tuple 
	 * 
	 * @param table: Table name to fetch from
	 * @param field: field you want along id
	 */
	public static Map<Integer, Integer> getPendingRequests() {
		if (debugPrints)
			System.out.println("[getPendingRequests]");
		dbConnect();

		String sql = "select id, employee_id from Request where status = ?";
		Map<Integer, Integer> tuples = new HashMap<>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Pending");
			ResultSet rst = pstmt.executeQuery();

			while (rst.next())
				tuples.put(rst.getInt("id"), rst.getInt("employee_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return tuples;
	}
	
	
	//7. Filter pets by age - Chase
	public static List<String> getPetsByAge(int age) {
		
		dbConnect();
		String sql = "select name from Pet where age = ?";
		List<String> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, age);
			ResultSet rst = pstmt.executeQuery();	
			while(rst.next())
				list.add(rst.getString("name"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	//4. Fetch the Customers - Chase
	public static List<Customer> fetchCustomers() {
		dbConnect();
		String sql = "SELECT * FROM Customer";
		List <Customer> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next() ) {

				String name = r.getString("name");
				String phone = r.getString("phone_Number");
				Date date_joined = r.getDate("date_joined");
				Date birthdate = r.getDate("birthday");
				
				Customer c = new Customer(name, phone, date_joined, birthdate);
				c.setId(r.getInt("id"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	
	//4. Insert a new pet - Chase
	public static void insertPet(Pet pet) {
		 dbConnect();
		 String sql="insert into pet(Request_id, name, species, age, date_acquired, sex, color, breed, vaccinated, neutered, cost) "
		 		+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, 10);
			pstmt.setString(2, pet.getName());
			pstmt.setString(3, pet.getSpecies());
			pstmt.setDouble(4, pet.getAge());
			pstmt.setString(5, pet.getDate_acquired());
			pstmt.setString(6, pet.getSex());
			pstmt.setString(7, pet.getColor());
			pstmt.setString(8, pet.getBreed());
			pstmt.setBoolean(9, pet.isVaccinated());
			pstmt.setBoolean(10, pet.isNeutered());
			pstmt.setDouble(11, pet.getCost());
			
			//String "1999-03-13".split("-")[0]
			//Date d = new Date([0], 
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 dbClose();
	}
	
	
	public static Pet findPet(int id) throws InvalidSearchException {
		List<Pet> pets = fetchPets();
		for (Pet pet : pets) {
			if (pet.getId() == id)
				return pet;
		}
		throw new InvalidSearchException("Pet not found");
	}

	public static void insertRequest(Request r) {
		dbConnect();
		String sql = "insert into request(customer_id, pet_id, date, status, employee_id) " + "values (?,?,?,?, ?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, r.getCustomer_id());
			pstmt.setInt(2, r.getPet_id());
			pstmt.setDate(3, r.getDate());
			pstmt.setString(4, r.getStatus());
			pstmt.setInt(5, r.getEmployee_id());
			pstmt.executeUpdate();

			System.out.println("Request inserted successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		dbClose();
	}

	
	// Validations

}