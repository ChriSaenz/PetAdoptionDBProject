package com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
	
	static Connection con;
	
	private static void dbConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adoption_sys","root","Password123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void login(String username, String password) {
		
	}
}