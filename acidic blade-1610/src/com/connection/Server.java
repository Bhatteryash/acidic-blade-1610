package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {

	public static Connection provideConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/mini_project";

		try {
			conn = DriverManager.getConnection(url, "root", "123456789");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return conn;

	}
}
