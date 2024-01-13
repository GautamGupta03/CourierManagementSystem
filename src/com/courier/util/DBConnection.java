package com.courier.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

	private static Connection connection = null;

	private DBConnection() {
		// private constructor to prevent instantiation
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Properties properties = new Properties();
				InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

				if (inputStream == null) {
					System.err.println("Unable to find db.properties file");
				} else {
					properties.load(inputStream);

					String url = properties.getProperty("db.url");
					String user = properties.getProperty("db.user");
					String password = properties.getProperty("db.password");

					connection = DriverManager.getConnection(url, user, password);
				}
			} catch (Exception e) {
				e.printStackTrace(); // Handle exception properly in a real-world scenario
			}
		}
		return connection;
	}
}