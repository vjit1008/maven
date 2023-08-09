package com.gurukul.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private Connection conn = null;

    DataSource() {
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	    e.getMessage();
	    e.printStackTrace();
	} finally {
	    System.out.println("Registered the driver");
	}
    }

    public Connection getConnection() {
	try {
//	    System.out.println("connecting to mySQL database");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/", "root", "Rajshree@123#");
	} catch (SQLException ex) {
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());

	}
	return conn;
    }
}
