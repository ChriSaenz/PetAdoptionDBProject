package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.LoginFailedException;
import com.objects.User;

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
	
	private static List<User> fetchUsers() {
		dbConnect();
		String sql = "select * from employee";
		List <User> list = new ArrayList<>();
		try {
			PreparedStatement s = con.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while (r.next() ) {
				String username = r.getString("username");
				String password = r.getString("password");
				int type = r.getInt("type");
				Type eType = null;
				switch (type) {
				case 1:
					eType = Type.CUSTOMER;
					break;
				case 2:
					eType = Type.EMPLOYEE;
					break;
				case 3:
					eType = Type.ADMIN;
					break;
				}
				User u = new User(username, password, eType);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public static Type login(String username, String password) throws LoginFailedException {
		List<User> users = fetchUsers();
		for (User u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) return u.getType();
		}
		throw new LoginFailedException("Unable to find user in database");
	}

	public static void registerUser(User u) {
		String sql = "insert into user (username, password, type) "
				+ "values (?,?,?)";
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, u.getUsername());
			s.setString(2, u.getPassword());
			int iType;
			switch (u.getType()) {
			case EMPLOYEE:
				iType = 2;
				break;
			case ADMIN:
				iType = 3;
				break;
			default:
				iType = 1;
				break;
			}
			s.setInt(3, iType);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}