package com.jms.projekt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcDao {

	public static Connection getConnection(){
		
		String driver="";
		String url="";
		Connection con=null;
		
		try {
			Class.forName(driver);
			con= DriverManager.getConnection(url);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
}
