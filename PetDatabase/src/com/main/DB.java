package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.objects.Employee;

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
	
	public static boolean login(String username, String password) {
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				System.out.println("Login successful! Hello " + u.getName());
				return true;
			}
		}
		return false;
	}

	public static boolean adminLogin(String username, String password) {
		List<Employee> users = fetchUsers();
		for (Employee u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password) && u.isAdmin()) {
				System.out.println("Login successful! Hello " + u.getName());
				return true;
			}
		}
		return false;
	}
}