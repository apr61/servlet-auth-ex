package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDetails {
	
	private static final String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "rootroot";
	private static final String URL = "jdbc:mysql://localhost:3306/auth_servlet";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(LOAD_DRIVER);
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
