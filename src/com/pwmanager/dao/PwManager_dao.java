package com.pwmanager.dao;

import java.sql.*;

import com.pwmanager.modal.PwManager;

public class PwManager_dao {
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static boolean validate(PwManager pwmanager){
		boolean valid = false;
		try {
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public static int insert(PwManager pwmanager){
		int status = 0;
		try {
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	public static PwManager retrievePwTable(PwManager pwmanager){
		PwManager manager = new PwManager();
		try {
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return manager;
	}

	public static PwManager retrievePw(PwManager pwmanager){
		PwManager manager = new PwManager();
		try {
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return manager;
	}
	
}
