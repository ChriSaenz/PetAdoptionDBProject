package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidSearchException;
import com.objects.Customer;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Request;

public class DB {
	
	static Connection con;
	
	private static void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded ||| ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adoption_system", "root", "Password123");
			System.out.println("Connection Established\n");
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
	
	//	Creates and returns a list of all employees.
	public static List<Employee> fetchUsers() {
		dbConnect();
		String sql = "select * from employee";
		List <Employee> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next() ) {
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
			while(r.next()) {
				Pet p = new Pet(
								r.getInt("request_id"),
								r.getString("name"),
								r.getString("species"),
								r.getInt("age"),
								r.getString("date_acquired"),
								r.getString("sex"),
								r.getString("color"),
								r.getString("breed"),
								r.getBoolean("vaccinated"),
								r.getBoolean("neutered"),
								r.getDouble("cost"));
				p.setId(r.getInt("id"));
				list.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	public static List<Pet> fetchPets(String column, String value) {
		dbConnect();
		
		String sql = "SELECT * FROM Pet WHERE ? = ?";
		List<Pet> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, column);
			ps.setString(2, value);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Pet p = new Pet(r.getInt("request_id"),
						r.getString("name"),
						r.getString("species"),
						r.getInt("age"),
						r.getString("date_acquired"),
						r.getString("sex"),
						r.getString("color"),
						r.getString("breed"),
						r.getBoolean("vaccinated"),
						r.getBoolean("neutered"),
						r.getDouble("cost"));
				p.setId(r.getInt("id"));
				list.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	//	TODO: Test these methods for correctness
	public static List<Request> fetchRequests() {
		return fetchRequests(null, null);
	}
	public static List<Request> fetchRequests(String column, String value) {
		dbConnect();
		
		String sql = "SELECT * FROM request";
		List<Request> list = new ArrayList<>();
		if(column != null && value != null)
			sql = sql + " WHERE ? = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if(column != null && value != null) {
				ps.setString(1, column);
				ps.setString(2, value);
			}
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Request req = new Request(r.getInt("id"),
							r.getInt("customer_id"),
							r.getInt("pet_id"),
							r.getString("date"),
							r.getString("status"),
							r.getInt("employee_id"));
				list.add(req);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	public static Employee findEmployee(String username, String password) throws InvalidSearchException{
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		throw new InvalidSearchException("Employee "+ username +" does not exist");
	}
	


	public static Employee findEmployee(int id) throws InvalidSearchException {
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if(u.getId() == id) {
				return u;
			}
		}
		throw new InvalidSearchException("Employee "+ id +" does not exist");
	}

	public static void removeEmployee(int id) {
		dbConnect();
		String sql="delete from employee where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}
	
	
	// Diego's note: Returns a list of all Pets name
	public static List<String> getAllPetsName() 
	{
		System.out.println("[GetAllPetsName]");
		dbConnect();
		
		String sql = "select name from Pet";
		List<String> list = new ArrayList<>();
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next())
				list.add(rst.getString("name"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}

	
	// Diego's note: Returns a list of all Species
	public static List<String> getAllPetSpecies() {
		System.out.println("[GetAllPetsSpecies]");
		dbConnect();
		
		String sql = "select distinct species from Pet";
		List<String> list = new ArrayList<>();
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next())
				list.add(rst.getString("species"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}

	// Diego's note: Returns a list of all Pets name by Species
	// (Does not check for Unlisted Species)
	public static List<String> getAllPetsNameBySpecies(String species) {
		System.out.println("[GetAllPetsNameBySpecies]");
		dbConnect();
		
		String sql = "select name from Pet where species = ?";
		List<String> list = new ArrayList<>();
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, species);
			ResultSet rst = pstmt.executeQuery();			
			
			while(rst.next())
				list.add(rst.getString("name"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}

	public static Customer findCustomer(int customerID) {
		System.out.println("[findCustomer]");
		dbConnect();
		
		Customer cus = new Customer();
		
		String sql = "select * from Customer where id = ?";
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerID);
			ResultSet rst = pstmt.executeQuery();
			rst.next();
			
			int nid = rst.getInt("id");
			String nname = rst.getString("name"),
				   nphone = rst.getString("phone_number");
			Date ndate = rst.getDate("date_joined"),
				 nbirthday = rst.getDate("birthday");
			
			cus = new Customer(nid, nname, nphone, ndate, nbirthday);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		dbClose();
		return cus;
	}

	// Diego's Note: Inserts a new employee into the Employee Table
	public static void insertEmployee(Employee emp) {
		System.out.println("[insertEmployee]");
		dbConnect();
		
		String sql = "insert into Employee (username, password, name, phone, salary, title, admin) values"
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		try 
		{
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
		} 
		catch (SQLException e)
		{
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
		
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (status == 1) ? "Approved" : "Rejected");
			pstmt.setInt(2, requestNum);

			pstmt.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		dbClose();
	}
}
