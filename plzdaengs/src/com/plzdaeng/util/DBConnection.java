package com.plzdaeng.util;

import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName(SiteConstance.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection makeConnection() throws SQLException {
		
		return DriverManager.getConnection(SiteConstance.DB_URL,SiteConstance.DB_USERNAME,SiteConstance.DB_PASSWORD);
	}
	
	public static Connection makeConnectplzdb() throws SQLException {
		
		return DriverManager.getConnection(SiteConstance.DB_URL,"plz","plz");
	}
}
