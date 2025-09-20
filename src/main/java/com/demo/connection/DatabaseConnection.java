package com.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public Connection getConnction() {
		try {
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport-management", "root",
					"admin@123");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
