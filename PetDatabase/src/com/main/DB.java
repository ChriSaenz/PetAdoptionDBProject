package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidSearchException;
import com.objects.Customer;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Receipt;

public class DB {
	
	static Connection con;
	
	private static void dbConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adoption_sys","root","Password123");
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
	
	private static List<Employee> fetchUsers() {
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
	
	public static List<Pet> fetchPetsByColumnValue(String column, String value) {
		dbConnect();
		
		String sql = "SELECT * FROM Pet WHERE ? = ?";
		List<Pet> list = fetchPets();
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

	public static List<Receipt> getReceipts() {
		List<Receipt> list = new ArrayList<>();
		dbConnect();
		String sql = "select * from receipt";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Receipt re = new Receipt(
								r.getInt("id"),
								r.getInt("employee_id"),
								r.getInt("customer_id"),
								r.getInt("request_id"),
								r.getString("date"),
								r.getDouble("cost"));
				list.add(re);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public static List<Customer> fetchCustomers() {
		dbConnect();
		String sql = "select * from customer";
		List <Customer> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next() ) {
				System.out.println("here");
				int id = r.getInt("id");
				String name = r.getString("name");
				String phone = r.getString("phone_number");
				Date date_joined = r.getDate("date_joined");
				Date birthday = r.getDate("birthday");
				Customer c = new Customer(id, name, phone, date_joined, birthday);
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	public static Customer findCustomer(int id) throws InvalidSearchException {
		List<Customer> list = fetchCustomers();
		for (Customer c : list) {
			if (c.getId() == id) return c;
		}
		throw new InvalidSearchException("Customer not found.");
	}

	public static void insertCustomer(Customer customer) {
		 dbConnect();
		 String sql="insert into customer(name,phone_number,date_joined, birthday) "
		 		+ "values (?,?,?,?)";

		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhone_number());
			pstmt.setDate(3, customer.getDate_joined());
			pstmt.setDate(4, customer.getBirthday());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 dbClose();			
	}
}