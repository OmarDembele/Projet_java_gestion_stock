package com.geststockapp.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class DBManager {
	private static Connection connection = null;
	public static Connection getConnection () throws Exception {
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection ("jdbc:mysql://localhost/dbproduct", "root", "");
			return connection;
		} 
		catch (ClassNotFoundException e) {
				throw new Exception ("Driver Class not found : '" + e.getMessage () + "' ");
		} 
		catch (SQLException e) {
				throw new Exception ("Error : Unable to open connection with database !");
			}
		}
}