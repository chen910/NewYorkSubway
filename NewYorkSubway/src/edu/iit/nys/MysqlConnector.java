package edu.iit.nys;
import java.sql.*;
public class MysqlConnector {

    		public static Connection getMySqlConnection() {

    			Connection connection = null;

    			try {
    				Class.forName("com.mysql.jdbc.Driver");
    			} catch (ClassNotFoundException e) {
    				System.out.println("Error loading MySQL JDBC Driver.");
    				e.printStackTrace();

    			}

    			try {
    				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newyorksubway", "root", "123456");

    			} catch (SQLException e) {
    				System.out.println("Connection Failed! Check output console");
    				e.printStackTrace();
    			}

    			if (connection != null) {
    				System.out.println("Succeed to make connection!");
    			} else {
    				System.out.println("Failed to make connection!");
    			}
    			
    			return connection;
    		}
}
