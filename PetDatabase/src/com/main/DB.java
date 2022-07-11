package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidSearchException;
import com.objects.Employee;
import com.objects.Pet;
import com.objects.Request;

public class DB {
	
	static Connection con;
	
	private static void dbConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adoption_system","root","Password123");
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
		
		String sql = "SELECT * FROM Pet WHERE " + column + " = ?";
		List<Pet> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, value);
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
			sql = sql + " WHERE " + column + " = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if(column != null && value != null) {
				ps.setString(1, value);
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
}