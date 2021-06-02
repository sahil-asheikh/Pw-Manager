package com.pwmanager.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTable_DAO {
	
//	get Connection method with database name
	public static Connection getConnection(String dbname){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbname, "root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//	get Connection method without database name
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//	method to check if database if exists or not
	public static boolean databaseExists(String dbname) {
		boolean exist = false;
		try {
			Connection con = DatabaseTable_DAO.getConnection();
			ResultSet rs = con.getMetaData().getCatalogs();
			while (rs.next()) {
				String catalogs = rs.getString(1);
				if(dbname.equals(catalogs)){		// if database is exists
					exist = true;	// name exist true
					break;	// if table found then break the loop
				} else {				// if database not exists
					exist = false;	// false
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exist;
	}
	
//	method to create database
	public static int createDatabase (String dbname) {
		int i = 1;
//		if database name is not null and database is not exists with same name
		if (dbname != null && !databaseExists(dbname)) {
			try {
				Connection con = DatabaseTable_DAO.getConnection();
				Statement stmt = con.createStatement();
		        i = stmt.executeUpdate("CREATE DATABASE " + dbname);
			} catch (Exception e) {
				
			}
		}
		return i;
	}
	
//	method to check if table is exists in the database or not
	public static boolean tableExists (String dbname, String table_name) {
		boolean exist = false;
		if (dbname != null && table_name != null) {
			try {
				Connection con = DatabaseTable_DAO.getConnection(dbname);
				DatabaseMetaData dbm = con.getMetaData();
				ResultSet tables = dbm.getTables(null, null, table_name, null);
				while (tables.next()) { 
		            String tName = tables.getString("TABLE_NAME");
		            if (tName != null && tName.equals(table_name)) {
		                exist = true;
		                break;
		            }
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exist;
	}
	
//	method to create password table
	public static int createPasswordTable (String dbname, String table_name) {
		int i = 1;
		if (dbname != null && table_name != null) {
			try {
				Connection con = DatabaseTable_DAO.getConnection(dbname);
				Statement stmt = con.createStatement();

				String query = "CREATE TABLE " + table_name +
						"(id INT(3) not NULL, " +
						" password VARCHAR(20), " +
						" keyZ INT(3), " +
						" username VARCHAR(26), " +
						" user_email VARCHAR(30), " +
						" app_name VARCHAR(10), " +
						" url VARCHAR(30), " +
						" PRIMARY KEY ( id ))";
				i = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
//	method to create user table
	public static int createUserTable (String dbname, String table_name) {
		int i = 1;
		if (dbname != null && table_name != null) {
			try {
				Connection con = DatabaseTable_DAO.getConnection(dbname);
				Statement stmt = con.createStatement();

				String query = "CREATE TABLE users"
						+ "(username VARCHAR(20),"
						+ "password VARCHAR(20),"
						+ "email VARCHAR(50))";
				
				i = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
}
