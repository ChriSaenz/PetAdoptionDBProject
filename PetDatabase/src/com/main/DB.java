package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidSearchException;
import com.objects.*;

public class DB {

	static Connection con;

	private static void dbConnect() {
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

	private static List<Employee> fetchUsers() {
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

	private static Employee findEmployee(String username, String password) throws InvalidSearchException {
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		throw new InvalidSearchException("Unable to retrieve emplyee from database");
	}

	public static boolean login(String username, String password) {
		try {
			Employee u = findEmployee(username, password);
			System.out.println("Login successful! Hello " + u.getName());
			return true;
		} catch (InvalidSearchException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean adminLogin(String username, String password) {
		try {
			Employee u = findEmployee(username, password);
			if (u.isAdmin()) {
				System.out.println("Login successful! Hello " + u.getName());
				return true;
			}
			return false;
		} catch (InvalidSearchException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static Employee findEmployeeL(int id) throws InvalidSearchException {
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		throw new InvalidSearchException("Unable to retrieve emplyee from database");
	}
	
	public static void deleteEmployee(int id) {
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

	public static List<Pet> fetchPets() {
		dbConnect();
		String sql = "select * from pet";
		List<Pet> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next()) {
				int id = r.getInt("id");
				int request_id = r.getInt("Request_id");
				String name = r.getString("name");
				String species = r.getString("name");
				int age = r.getInt("age");
				String date_acquired = r.getString("date_acquired");
				String sex = r.getString("sex");
				String color = r.getString("color");
				String breed = r.getString("breed");
				boolean vaccinated = r.getBoolean("vaccinated");
				boolean neutered = r.getBoolean("neutered");
				double cost = r.getDouble("cost");
				Pet p = new Pet(request_id, name, species, age, date_acquired, sex, color, breed, vaccinated, neutered,
						cost);
				p.setId(id);
				list.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
}